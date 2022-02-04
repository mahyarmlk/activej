package io.activej.launchers.crdt;

import io.activej.config.Config;
import io.activej.config.converter.ConfigConverter;
import io.activej.crdt.storage.cluster.RendezvousPartitionGroup;
import io.activej.crdt.storage.cluster.RendezvousPartitionScheme;
import io.activej.crdt.storage.cluster.SimplePartitionId;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.*;

import static io.activej.common.Utils.mapOf;
import static io.activej.common.Utils.setOf;
import static io.activej.launchers.crdt.ConfigConverters.*;
import static org.junit.Assert.*;

public class ConfigConverterTest {
	@Test
	public void ofSimplePartitionIdTest() {
		Map<String, String> properties = mapOf(
				"partition1", "partitioningA|testA",
				"partition2", "partitioningB|testB|255.255.255.255:9000",
				"partition3", "partitioningC|testC||255.255.255.255:9001",
				"partition4", "partitioningD|testD|255.255.255.255:9000|255.255.255.255:9001",
				"localPartition", "partitioningD|testD|localhost:9000|localhost:9001"
		);
		Config config = Config.ofMap(properties);
		ConfigConverter<SimplePartitionId> converter = ofSimplePartitionId();

		SimplePartitionId partitionId1 = config.get(converter, "partition1");
		SimplePartitionId expected1 = SimplePartitionId.of("partitioningA", "testA", null, null);
		assertPartitionsFullyEquals(expected1, partitionId1);

		SimplePartitionId partitionId2 = config.get(converter, "partition2");
		SimplePartitionId expected2 = SimplePartitionId.of("partitioningB", "testB",
				new InetSocketAddress("255.255.255.255", 9000),
				null
		);
		assertPartitionsFullyEquals(expected2, partitionId2);

		SimplePartitionId partitionId3 = config.get(converter, "partition3");
		SimplePartitionId expected3 = SimplePartitionId.of("partitioningC", "testC",
				null,
				new InetSocketAddress("255.255.255.255", 9001)
		);
		assertPartitionsFullyEquals(expected3, partitionId3);

		SimplePartitionId partitionId4 = config.get(converter, "partition4");
		SimplePartitionId expected4 = SimplePartitionId.of("partitioningD", "testD",
				new InetSocketAddress("255.255.255.255", 9000),
				new InetSocketAddress("255.255.255.255", 9001)
		);
		assertPartitionsFullyEquals(expected4, partitionId4);

		SimplePartitionId localPartitionId = config.get(converter, "localPartition");
		SimplePartitionId localExpected = SimplePartitionId.of("partitioningD", "testD",
				new InetSocketAddress("localhost", 9000),
				new InetSocketAddress("localhost", 9001)
		);
		assertPartitionsFullyEquals(localExpected, localPartitionId);
		assertEquals(partitionId4, localPartitionId);
	}

	@Test
	public void ofSimplePartitionGroupTest() {
		String partitioningId = "partitioningA";
		Map<String, String> properties = mapOf(
				"ids",
				partitioningId + "|testA|101.101.101.101:9000|101.101.101.101:9001," +
						partitioningId + "|testB|102.102.102.102:9000|102.102.102.102:9001," +
						partitioningId + "|testC|103.103.103.103:9000|103.103.103.103:9001," +
						partitioningId + "|testD|104.104.104.104:9000|104.104.104.104:9001",
				"replicas", "2",
				"repartition", "true",
				"active", "true"
		);
		Config config = Config.ofMap(properties);
		ConfigConverter<RendezvousPartitionGroup<SimplePartitionId>> converter = ofPartitionGroup(ofSimplePartitionId());

		RendezvousPartitionGroup<SimplePartitionId> partitionGroup = converter.get(config);

		Set<SimplePartitionId> expectedPartitionIds = setOf(
				SimplePartitionId.of(partitioningId, "testA",
						new InetSocketAddress("101.101.101.101", 9000),
						new InetSocketAddress("101.101.101.101", 9001)
				),
				SimplePartitionId.of(partitioningId, "testB",
						new InetSocketAddress("102.102.102.102", 9000),
						new InetSocketAddress("102.102.102.102", 9001)
				),
				SimplePartitionId.of(partitioningId, "testC",
						new InetSocketAddress("103.103.103.103", 9000),
						new InetSocketAddress("103.103.103.103", 9001)
				),
				SimplePartitionId.of(partitioningId, "testD",
						new InetSocketAddress("104.104.104.104", 9000),
						new InetSocketAddress("104.104.104.104", 9001)
				)
		);

		assertSetsFullyEquals(expectedPartitionIds, partitionGroup.getPartitions());
		assertEquals(2, partitionGroup.getReplicas());
		assertTrue(partitionGroup.isActive());
		assertTrue(partitionGroup.isRepartition());
	}

	@Test
	public void ofSimplePartitionSchemeTest() {
		String partitioningId = "partitioningA";
		Map<String, String> properties = new HashMap<>();

		properties.put("partitionGroup.1.ids", "" +
				partitioningId + "|testA|101.101.101.101:9000|101.101.101.101:9001," +
				partitioningId + "|testB|102.102.102.102:9000|102.102.102.102:9001," +
				partitioningId + "|testC|103.103.103.103:9000|103.103.103.103:9001," +
				partitioningId + "|testD|104.104.104.104:9000|104.104.104.104:9001");
		properties.put("partitionGroup.1.replicas", "3");
		properties.put("partitionGroup.1.repartition", "true");
		properties.put("partitionGroup.1.active", "true");

		properties.put("partitionGroup.2.ids", "" +
				partitioningId + "|testE|105.105.105.105:9000|105.105.105.105:9001," +
				partitioningId + "|testF|106.106.106.106:9000|106.106.106.106:9001," +
				partitioningId + "|testG|107.107.107.107:9000|107.107.107.107:9001");
		properties.put("partitionGroup.2.replicas", "2");
		properties.put("partitionGroup.2.repartition", "false");
		properties.put("partitionGroup.2.active", "false");

		Config config = Config.ofMap(properties);
		ConfigConverter<RendezvousPartitionScheme<SimplePartitionId>> converter = ofRendezvousPartitionScheme();

		RendezvousPartitionScheme<SimplePartitionId> partitionScheme = converter.get(config);
		Set<SimplePartitionId> partitions = partitionScheme.getPartitions();

		Set<SimplePartitionId> expectedPartitionIds = setOf(
				SimplePartitionId.of(partitioningId, "testA",
						new InetSocketAddress("101.101.101.101", 9000),
						new InetSocketAddress("101.101.101.101", 9001)
				),
				SimplePartitionId.of(partitioningId, "testB",
						new InetSocketAddress("102.102.102.102", 9000),
						new InetSocketAddress("102.102.102.102", 9001)
				),
				SimplePartitionId.of(partitioningId, "testC",
						new InetSocketAddress("103.103.103.103", 9000),
						new InetSocketAddress("103.103.103.103", 9001)
				),
				SimplePartitionId.of(partitioningId, "testD",
						new InetSocketAddress("104.104.104.104", 9000),
						new InetSocketAddress("104.104.104.104", 9001)
				),
				SimplePartitionId.of(partitioningId, "testE",
						new InetSocketAddress("105.105.105.105", 9000),
						new InetSocketAddress("105.105.105.105", 9001)
				),
				SimplePartitionId.of(partitioningId, "testF",
						new InetSocketAddress("106.106.106.106", 9000),
						new InetSocketAddress("106.106.106.106", 9001)
				),
				SimplePartitionId.of(partitioningId, "testG",
						new InetSocketAddress("107.107.107.107", 9000),
						new InetSocketAddress("107.107.107.107", 9001)
				)
		);

		assertSetsFullyEquals(expectedPartitionIds, partitions);
	}

	private static void assertPartitionsFullyEquals(SimplePartitionId expected, SimplePartitionId actual) {
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getCrdtAddress(), actual.getCrdtAddress());
		assertEquals(expected.getRpcAddress(), actual.getRpcAddress());
	}

	private static void assertSetsFullyEquals(Set<SimplePartitionId> expected, Set<SimplePartitionId> actual) {
		assertEquals(expected.size(), actual.size());

		expected = sort(expected);
		actual = sort(actual);

		Iterator<SimplePartitionId> expectedIt = expected.iterator();
		Iterator<SimplePartitionId> actualIt = actual.iterator();

		while (expectedIt.hasNext()) {
			assertPartitionsFullyEquals(expectedIt.next(), actualIt.next());
		}

		assertFalse(actualIt.hasNext());
	}

	private static SortedSet<SimplePartitionId> sort(Set<SimplePartitionId> set) {
		TreeSet<SimplePartitionId> treeSet = new TreeSet<>(Comparator.comparing(SimplePartitionId::getId));
		treeSet.addAll(set);
		return treeSet;
	}
}
