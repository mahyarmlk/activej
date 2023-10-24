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

package io.activej.cube.aggregation;

import io.activej.promise.Promise;
import io.activej.reactor.AbstractReactive;
import io.activej.reactor.Reactor;

import java.util.Set;

import static io.activej.reactor.Reactive.checkInReactorThread;

public final class NoOpChunkLocker extends AbstractReactive
	implements IChunkLocker {

	private NoOpChunkLocker(Reactor reactor) {
		super(reactor);
	}

	public static NoOpChunkLocker create(Reactor reactor) {
		return new NoOpChunkLocker(reactor);
	}

	@Override
	public Promise<Void> lockChunks(Set<Long> chunkIds) {
		checkInReactorThread(this);
		return Promise.complete();
	}

	@Override
	public Promise<Void> releaseChunks(Set<Long> chunkIds) {
		checkInReactorThread(this);
		return Promise.complete();
	}

	@Override
	public Promise<Set<Long>> getLockedChunks() {
		checkInReactorThread(this);
		return Promise.of(Set.of());
	}
}
