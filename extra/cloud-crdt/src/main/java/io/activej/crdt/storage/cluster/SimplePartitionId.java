/*
 * Copyright (C) 2020 ActiveJ LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.activej.crdt.storage.cluster;

import io.activej.common.exception.MalformedDataException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;

import static io.activej.common.StringFormatUtils.parseInetSocketAddress;

public final class SimplePartitionId {
	private final String partitionGroupId;
	private final String id;
	private final @Nullable InetSocketAddress crdtAddress;
	private final @Nullable InetSocketAddress rpcAddress;

	private SimplePartitionId(String partitionGroupId, String id, @Nullable InetSocketAddress crdtAddress, @Nullable InetSocketAddress rpcAddress) {
		this.partitionGroupId = partitionGroupId;
		this.id = id;
		this.crdtAddress = crdtAddress;
		this.rpcAddress = rpcAddress;
	}

	public static SimplePartitionId of(String partitionGroupId, String id, @Nullable InetSocketAddress crdt, @Nullable InetSocketAddress rpc) {
		return new SimplePartitionId(partitionGroupId, id, crdt, rpc);
	}

	public static SimplePartitionId ofCrdtAddress(String partitionGroupId, @NotNull String id, @NotNull InetSocketAddress crdtAddress) {
		return new SimplePartitionId(partitionGroupId, id, crdtAddress, null);
	}

	public static SimplePartitionId ofRpcAddress(String partitionGroupId, @NotNull String id, @NotNull InetSocketAddress rpcAddress) {
		return new SimplePartitionId(partitionGroupId, id, null, rpcAddress);
	}

	public static SimplePartitionId parseString(String string) throws MalformedDataException {
		String[] split = string.split("\\|");
		if (split.length > 4) {
			throw new MalformedDataException("");
		}
		String partitionGroupId = split[0];
		String id = split[1];
		InetSocketAddress crdtAddress = split.length > 2 && !split[2].trim().isEmpty() ? parseInetSocketAddress(split[2]) : null;
		InetSocketAddress rpcAddress = split.length > 3 && !split[3].trim().isEmpty() ? parseInetSocketAddress(split[3]) : null;

		return new SimplePartitionId(partitionGroupId, id, crdtAddress, rpcAddress);
	}

	public String getPartitionGroupId() {
		return partitionGroupId;
	}

	public String getId() {
		return id;
	}

	public int idHashCode() {
		return id.hashCode();
	}

	@SuppressWarnings("NullableProblems")
	public InetSocketAddress getCrdtAddress() {
		return crdtAddress;
	}

	@SuppressWarnings("NullableProblems")
	public InetSocketAddress getRpcAddress() {
		return rpcAddress;
	}

	private static String addressToString(@Nullable InetSocketAddress address) {
		return address == null ?
				"" :
				address.getAddress().getHostAddress() + ":" + address.getPort();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SimplePartitionId that = (SimplePartitionId) o;

		if (!partitionGroupId.equals(that.partitionGroupId)) return false;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		int result = partitionGroupId.hashCode();
		result = 31 * result + id.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return partitionGroupId + '|' + id + '|' + addressToString(crdtAddress) + '|' + addressToString(rpcAddress);
	}
}
