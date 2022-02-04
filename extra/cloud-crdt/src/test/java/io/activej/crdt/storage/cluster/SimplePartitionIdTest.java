package io.activej.crdt.storage.cluster;

import io.activej.common.exception.MalformedDataException;
import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.assertEquals;

public class SimplePartitionIdTest {

	private static final String HOSTNAME = "255.255.255.255";

	@Test
	public void testLocalEquality() {
		String partitioningId = "partitioningA";
		String id = "test";
		SimplePartitionId localPartitionId = SimplePartitionId.of(partitioningId, id, null, null);
		SimplePartitionId partitionId = SimplePartitionId.of(partitioningId, id, address(9000), address(9001));

		assertEquals(localPartitionId, partitionId);
		assertEquals(localPartitionId.hashCode(), partitionId.hashCode());
	}

	@Test
	public void testParse() {
		doTestParse("partitioningA|test||", SimplePartitionId.of("partitioningA", "test", null, null));
		doTestParse("partitioningA|test|255.255.255.255:9000|", SimplePartitionId.ofCrdtAddress("partitioningA", "test", address(9000)));
		doTestParse("partitioningA|test||255.255.255.255:9000", SimplePartitionId.ofRpcAddress("partitioningA", "test", address(9000)));
		doTestParse("partitioningA|test|255.255.255.255:9000|255.255.255.255:9001", SimplePartitionId.of("partitioningA", "test", address(9000), address(9001)));
	}

	@Test
	public void testParseTolerant() {
		doTestParse(SimplePartitionId.of("partitioningA","test", null, null), "partitioningA|test");
		doTestParse(SimplePartitionId.ofCrdtAddress("partitioningA", "test", address(9000)), "partitioningA|test|255.255.255.255:9000");
	}

	private void doTestParse(String expectedString, SimplePartitionId simplePartitionId) {
		String partitionIdString = simplePartitionId.toString();
		assertEquals(expectedString, partitionIdString);

		SimplePartitionId partitionId;
		try {
			partitionId = SimplePartitionId.parseString(partitionIdString);
		} catch (MalformedDataException e) {
			throw new AssertionError(e);
		}

		assertEquals(simplePartitionId.getId(), partitionId.getId());
		assertEquals(simplePartitionId.getCrdtAddress(), partitionId.getCrdtAddress());
		assertEquals(simplePartitionId.getRpcAddress(), partitionId.getRpcAddress());
	}

	private void doTestParse(SimplePartitionId expectedPartitionId, String sourceString) {
		SimplePartitionId partitionId;
		try {
			partitionId = SimplePartitionId.parseString(sourceString);
		} catch (MalformedDataException e) {
			throw new AssertionError(e);
		}

		assertEquals(expectedPartitionId.getId(), partitionId.getId());
		assertEquals(expectedPartitionId.getCrdtAddress(), partitionId.getCrdtAddress());
		assertEquals(expectedPartitionId.getRpcAddress(), partitionId.getRpcAddress());
	}

	private static InetSocketAddress address(int port) {
		return new InetSocketAddress(HOSTNAME, port);
	}
}
