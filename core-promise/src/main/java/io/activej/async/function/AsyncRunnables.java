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

package io.activej.async.function;

import io.activej.async.process.AsyncExecutor;
import io.activej.async.process.AsyncExecutors;
import io.activej.promise.Promise;
import io.activej.promise.Promises;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public final class AsyncRunnables {

	@Contract(pure = true)
	public static AsyncRunnable reuse(AsyncRunnable actual) {
		return new AsyncRunnable() {
			@Nullable Promise<Void> runningPromise;

			@Override
			public Promise<Void> run() {
				if (runningPromise != null) return runningPromise;
				runningPromise = actual.run();
				Promise<Void> runningPromise = this.runningPromise;
				runningPromise.whenComplete(() -> this.runningPromise = null);
				return runningPromise;
			}
		};
	}

	@Contract(pure = true)
	public static AsyncRunnable coalesce(AsyncRunnable actual) {
		AsyncFunction<Void, Void> fn = Promises.coalesce(() -> null, (a, v) -> {}, a -> actual.run());
		return () -> fn.apply(null);
	}

	@Contract(pure = true)
	public static AsyncRunnable buffer(AsyncRunnable actual) {
		return buffer(1, Integer.MAX_VALUE, actual);
	}

	@Contract(pure = true)
	public static AsyncRunnable buffer(int maxParallelCalls, int maxBufferedCalls, AsyncRunnable runnable) {
		return ofExecutor(AsyncExecutors.buffered(maxParallelCalls, maxBufferedCalls), runnable);
	}

	@Contract(pure = true)
	public static AsyncRunnable ofExecutor(AsyncExecutor executor, AsyncRunnable runnable) {
		return () -> executor.execute(runnable::run);
	}
}
