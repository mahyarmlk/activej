package io.activej.crdt.storage.cluster;

import io.activej.async.process.AsyncCloseable;
import io.activej.crdt.CrdtData;
import io.activej.crdt.CrdtException;
import io.activej.crdt.CrdtServer;
import io.activej.crdt.RemoteCrdtStorage;
import io.activej.crdt.function.CrdtFunction;
import io.activej.crdt.storage.ICrdtStorage;
import io.activej.crdt.storage.local.MapCrdtStorage;
import io.activej.crdt.util.CrdtDataBinarySerializer;
import io.activej.datastream.consumer.StreamConsumers;
import io.activej.datastream.supplier.StreamSupplier;
import io.activej.datastream.supplier.StreamSuppliers;
import io.activej.eventloop.Eventloop;
import io.activej.net.AbstractReactiveServer;
import io.activej.reactor.Reactor;
import io.activej.reactor.nio.NioReactor;
import io.activej.test.rules.ByteBufRule;
import io.activej.test.rules.EventloopRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static io.activej.crdt.function.CrdtFunction.ignoringTimestamp;
import static io.activej.promise.TestUtils.await;
import static io.activej.promise.TestUtils.awaitException;
import static io.activej.reactor.Reactor.getCurrentReactor;
import static io.activej.serializer.BinarySerializers.INT_SERIALIZER;
import static io.activej.serializer.BinarySerializers.UTF8_SERIALIZER;
import static io.activej.test.TestUtils.getFreePort;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public final class TestDyingPartitions {
	private static final int SERVER_COUNT = 5;
	private static final int REPLICATION_COUNT = 3;
	private static final CrdtFunction<Integer> CRDT_FUNCTION = ignoringTimestamp(Integer::max);
	private static final CrdtDataBinarySerializer<String, Integer> SERIALIZER = new CrdtDataBinarySerializer<>(UTF8_SERIALIZER, INT_SERIALIZER);

	@ClassRule
	public static final EventloopRule eventloopRule = new EventloopRule();

	@ClassRule
	public static final ByteBufRule byteBufRule = new ByteBufRule();

	private Map<Integer, AbstractReactiveServer> servers;
	private ClusterCrdtStorage<String, Integer, String> cluster;

	@Before
	public void setUp() throws Exception {
		servers = new LinkedHashMap<>();

		Map<String, ICrdtStorage<String, Integer>> clients = new HashMap<>();

		for (int i = 0; i < SERVER_COUNT; i++) {
			int port = getFreePort();
			Eventloop eventloop = Eventloop.create();
			MapCrdtStorage<String, Integer> storage = MapCrdtStorage.create(eventloop, CRDT_FUNCTION);
			InetSocketAddress address = new InetSocketAddress(port);
			CrdtServer<String, Integer> server = CrdtServer.builder(eventloop, storage, SERIALIZER)
				.withListenAddresses(address)
				.build();
			server.listen();
			assertNull(servers.put(port, server));
			new Thread(eventloop).start();

			clients.put("server_" + i, RemoteCrdtStorage.create(Reactor.getCurrentReactor(), address, SERIALIZER));
		}

		cluster = ClusterCrdtStorage.create(getCurrentReactor(),
			IDiscoveryService.of(
				RendezvousPartitionScheme.<String>builder()
					.withPartitionGroup(RendezvousPartitionGroup.builder(clients.keySet())
						.withReplicas(REPLICATION_COUNT)
						.withRepartition(true)
						.build())
					.withCrdtProvider(clients::get)
					.build()),
			CRDT_FUNCTION);
		await(cluster.start());
	}

	@After
	public void tearDown() {
		shutdownAllEventloops();
	}

	@Test
	public void testUploadWithDyingPartitions() {
		List<CrdtData<String, Integer>> data = new ArrayList<>();
		long now = getCurrentReactor().currentTimeMillis();
		for (int i = 0; i < 100_000; i++) {
			data.add(new CrdtData<>(String.valueOf(i), now, i + 1));
		}

		Exception exception = awaitException(StreamSuppliers.ofIterator(data.iterator())
			.streamTo(StreamConsumers.ofPromise(cluster.upload()
				.whenResult(this::shutdown2Servers))));

		assertThat(exception, instanceOf(CrdtException.class));
		assertEquals("Upload failed", exception.getMessage());
	}

	@Test
	public void testDownloadWithDyingPartitions() {
		List<CrdtData<String, Integer>> data = new ArrayList<>();
		long now = getCurrentReactor().currentTimeMillis();
		for (int i = 0; i < 500_000; i++) {
			data.add(new CrdtData<>(String.valueOf(i), now, i + 1));
		}

		await(StreamSuppliers.ofIterator(data.iterator())
			.streamTo(StreamConsumers.ofPromise(cluster.upload())));

		Exception exception = awaitException(cluster.download()
			.whenResult(this::shutdown2Servers)
			.then(StreamSupplier::toList));

		assertThat(exception, instanceOf(CrdtException.class));
		assertEquals("Download failed", exception.getMessage());
	}

	@SuppressWarnings("ConstantConditions")
	private void shutdown2Servers() {
		Iterator<AbstractReactiveServer> serverIterator = servers.values().iterator();
		for (int i = 0; i < 2; i++) {
			AbstractReactiveServer server = serverIterator.next();
			NioReactor reactor = server.getReactor();
			reactor.execute(() -> {
				for (SelectionKey key : reactor.getSelector().keys()) {
					Object attachment = key.attachment();
					if (attachment instanceof AsyncCloseable) {
						((AsyncCloseable) attachment).close();
					}
				}
			});
		}
	}

	private void shutdownAllEventloops() {
		for (AbstractReactiveServer server : servers.values()) {
			try {
				server.closeFuture().get();
				Thread eventloopThread = ((Eventloop) server.getReactor()).getEventloopThread();
				if (eventloopThread != null) {
					eventloopThread.join();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
