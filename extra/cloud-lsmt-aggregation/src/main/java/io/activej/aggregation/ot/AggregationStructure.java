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

package io.activej.aggregation.ot;

import io.activej.aggregation.JsonCodec_ChunkId;
import io.activej.aggregation.fieldtype.FieldType;
import io.activej.aggregation.measure.Measure;
import io.activej.common.initializer.AbstractBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.activej.common.Checks.checkArgument;

@SuppressWarnings("rawtypes")
public final class AggregationStructure {
	private final JsonCodec_ChunkId<?> chunkIdCodec;
	private final Map<String, FieldType> keyTypes = new LinkedHashMap<>();
	private final Map<String, FieldType> measureTypes = new LinkedHashMap<>();
	private final List<String> partitioningKey = new ArrayList<>();
	private final Map<String, Measure> measures = new LinkedHashMap<>();

	private AggregationStructure(JsonCodec_ChunkId<?> chunkIdCodec) {
		this.chunkIdCodec = chunkIdCodec;
	}

	public static Builder builder(JsonCodec_ChunkId<?> chunkIdCodec) {
		return new AggregationStructure(chunkIdCodec).new Builder();
	}

	public final class Builder extends AbstractBuilder<Builder, AggregationStructure> {
		private Builder() {}

		public Builder withKey(String keyId, FieldType type) {
			checkArgument(!keyTypes.containsKey(keyId), "Key '%s' has already been added", keyId);
			keyTypes.put(keyId, type);
			return this;
		}

		public Builder withKeys(Map<String, FieldType> keys) {
			for (String k : keys.keySet()) {
				withKey(k, keys.get(k));
			}
			return this;
		}

		public Builder withMeasure(String measureId, Measure aggregateFunction) {
			checkArgument(!measureTypes.containsKey(measureId), "Measure '%s' has already been added", measureId);
			measureTypes.put(measureId, aggregateFunction.getFieldType());
			measures.put(measureId, aggregateFunction);
			return this;
		}

		public Builder withMeasures(Map<String, Measure> measures) {
			for (String k : measures.keySet()) {
				withMeasure(k, measures.get(k));
			}
			return this;
		}

		@SuppressWarnings("UnusedReturnValue")
		public Builder withIgnoredMeasure(String measureId, FieldType measureType) {
			checkArgument(!measureTypes.containsKey(measureId), "Measure '%s' has already been added", measureId);
			measureTypes.put(measureId, measureType);
			return this;
		}

		public Builder withIgnoredMeasures(Map<String, FieldType> measures) {
			for (String k : measures.keySet()) {
				withIgnoredMeasure(k, measures.get(k));
			}
			return this;
		}

		public Builder withPartitioningKey(List<String> partitioningKey) {
			AggregationStructure.this.partitioningKey.addAll(partitioningKey);
			return this;
		}

		@Override
		protected AggregationStructure doBuild() {
			return AggregationStructure.this;
		}
	}

	public JsonCodec_ChunkId<?> getChunkIdCodec() {
		return chunkIdCodec;
	}

	public AggregationStructure withPartitioningKey(String... partitioningKey) {
		this.partitioningKey.addAll(List.of(partitioningKey));
		return this;
	}

	public List<String> getKeys() {
		return new ArrayList<>(keyTypes.keySet());
	}

	public List<String> getMeasures() {
		return new ArrayList<>(measures.keySet());
	}

	public Map<String, FieldType> getKeyTypes() {
		return keyTypes;
	}

	public Map<String, FieldType> getMeasureTypes() {
		return measureTypes;
	}

	public Measure getMeasure(String field) {
		return measures.get(field);
	}

	public FieldType getKeyType(String key) {
		return keyTypes.get(key);
	}

	public FieldType getMeasureType(String field) {
		return measureTypes.get(field);
	}

	public List<String> getPartitioningKey() {
		return partitioningKey;
	}

}
