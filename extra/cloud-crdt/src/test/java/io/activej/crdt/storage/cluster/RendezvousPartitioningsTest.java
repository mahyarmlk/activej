package io.activej.crdt.storage.cluster;

import io.activej.crdt.storage.cluster.DiscoveryService.PartitionScheme;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import static io.activej.common.Utils.setOf;
import static org.junit.Assert.assertEquals;

public class RendezvousPartitioningsTest {

	@Test
	public void testSameIds() {
		String partitionGroupA = "partitionGroupA";
		String partitionGroupB = "partitionGroupB";

		PartitionScheme<SimplePartitionId> partitionings = RendezvousPartitionScheme.<SimplePartitionId>create()
				.withPartitionGroup(RendezvousPartitionGroup.create(
								setOf(
										SimplePartitionId.of(partitionGroupA, "a", new InetSocketAddress(9001), new InetSocketAddress(8001)),
										SimplePartitionId.of(partitionGroupA, "b", new InetSocketAddress(9002), new InetSocketAddress(8002)),
										SimplePartitionId.of(partitionGroupA, "c", new InetSocketAddress(9003), new InetSocketAddress(8003))
								)
						).withReplicas(1)
						.withRepartition(false)
						.withActive(true))
				.withPartitionGroup(RendezvousPartitionGroup.create(
								setOf(
										SimplePartitionId.of(partitionGroupB, "a", new InetSocketAddress(9004), new InetSocketAddress(8004)),
										SimplePartitionId.of(partitionGroupB, "b", new InetSocketAddress(9005), new InetSocketAddress(8005)),
										SimplePartitionId.of(partitionGroupB, "c", new InetSocketAddress(9006), new InetSocketAddress(8006))
								)
						).withReplicas(1)
						.withRepartition(false)
						.withActive(false))
				.withPartitionIdHashFn(SimplePartitionId::idHashCode);

		List<SimplePartitionId> alive = Arrays.asList(
				SimplePartitionId.of("partitionGroupA", "a", new InetSocketAddress(9001), new InetSocketAddress(8001)),
				SimplePartitionId.of("partitionGroupA", "b", new InetSocketAddress(9002), new InetSocketAddress(8002)),
				SimplePartitionId.of("partitionGroupA", "c", new InetSocketAddress(9003), new InetSocketAddress(8003)),

				SimplePartitionId.of("partitionGroupB", "a", new InetSocketAddress(9004), new InetSocketAddress(8004)),
				SimplePartitionId.of("partitionGroupB", "b", new InetSocketAddress(9005), new InetSocketAddress(8005)),
				SimplePartitionId.of("partitionGroupB", "c", new InetSocketAddress(9006), new InetSocketAddress(8006))
		);

		Sharder<Integer> sharder = partitionings.createSharder(alive);

		assert sharder != null;

		for (int i = 0; i < 1_000_000; i++) {
			int[] sharded = sharder.shard(i);

			assertEquals(2, sharded.length);
			assertEquals(sharded[0], sharded[1] - 3);
		}
	}
}
