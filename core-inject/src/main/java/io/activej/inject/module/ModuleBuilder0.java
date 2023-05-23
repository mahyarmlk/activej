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

package io.activej.inject.module;

import io.activej.common.tuple.*;
import io.activej.inject.Key;
import io.activej.inject.Scope;
import io.activej.inject.binding.Binding;
import io.activej.inject.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public interface ModuleBuilder0<T> extends ModuleBuilder {
	/**
	 * The binding being built by this builder will be added to the binding graph trie at a given scope path
	 */
	ModuleBuilder1<T> in(Scope[] scope);

	/**
	 * The binding being built by this builder will be added to the binding graph trie at a given scope path
	 *
	 * @see #in(Scope[])
	 */
	ModuleBuilder1<T> in(Scope scope, Scope... scopes);

	/**
	 * The binding being built by this builder will be added to the binding graph trie at a given scope path
	 *
	 * @see #in(Scope[])
	 */
	ModuleBuilder1<T> in(Class<? extends Annotation> annotationClass, Class<?>... annotationClasses);

	/**
	 * Sets a binding which would be bound to a given key and added to the binding graph trie
	 */
	ModuleBuilder1<T> to(Binding<? extends T> binding);

	/**
	 * DSL shortcut for creating a binding that just calls a binding at a given key
	 * and {@link #to(Binding) binding it} to a current key.
	 */
	default ModuleBuilder1<T> to(Key<? extends T> implementation) {
		return to(Binding.to(implementation));
	}

	/**
	 * DSL shortcut for creating a binding that just calls a binding at a given key
	 * and {@link #to(Binding) binding it} to a current key.
	 *
	 * @see #to(Key)
	 */
	default ModuleBuilder1<T> to(Class<? extends T> implementation) {
		return to(Binding.to(implementation));
	}

	/**
	 * DSL shortcut for creating a binding from a given instance
	 * and {@link #to(Binding) binding it} to a current key.
	 */
	default ModuleBuilder1<T> toInstance(T instance) {
		return to(Binding.toInstance(instance));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default ModuleBuilder1<T> to(TupleConstructorN<? extends T> factory, Class<?>[] dependencies) {
		return to(Binding.to(factory, dependencies));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default ModuleBuilder1<T> to(TupleConstructorN<? extends T> factory, Key<?>[] dependencies) {
		return to(Binding.to(factory, dependencies));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default ModuleBuilder1<T> to(TupleConstructor0<? extends T> constructor) {
		return to(Binding.to(constructor));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1> ModuleBuilder1<T> to(
		TupleConstructor1<T1, ? extends T> constructor,
		Class<T1> dependency1
	) {
		return to(Binding.to(constructor, dependency1));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2> ModuleBuilder1<T> to(
		TupleConstructor2<T1, T2, ? extends T> constructor,
		Class<T1> dependency1, Class<T2> dependency2
	) {
		return to(Binding.to(constructor, dependency1, dependency2));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3> ModuleBuilder1<T> to(
		TupleConstructor3<T1, T2, T3, ? extends T> constructor,
		Class<T1> dependency1, Class<T2> dependency2, Class<T3> dependency3
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4> ModuleBuilder1<T> to(
		TupleConstructor4<T1, T2, T3, T4, ? extends T> constructor,
		Class<T1> dependency1, Class<T2> dependency2, Class<T3> dependency3, Class<T4> dependency4
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4, T5> ModuleBuilder1<T> to(
		TupleConstructor5<T1, T2, T3, T4, T5, ? extends T> constructor,
		Class<T1> dependency1, Class<T2> dependency2, Class<T3> dependency3, Class<T4> dependency4,
		Class<T5> dependency5
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4, dependency5));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4, T5, T6> ModuleBuilder1<T> to(
		TupleConstructor6<T1, T2, T3, T4, T5, T6, ? extends T> constructor,
		Class<T1> dependency1, Class<T2> dependency2, Class<T3> dependency3, Class<T4> dependency4,
		Class<T5> dependency5, Class<T6> dependency6
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4, dependency5, dependency6));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1> ModuleBuilder1<T> to(
		TupleConstructor1<T1, ? extends T> constructor,
		Key<T1> dependency1
	) {
		return to(Binding.to(constructor, dependency1));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2> ModuleBuilder1<T> to(
		TupleConstructor2<T1, T2, ? extends T> constructor,
		Key<T1> dependency1, Key<T2> dependency2
	) {
		return to(Binding.to(constructor, dependency1, dependency2));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3> ModuleBuilder1<T> to(
		TupleConstructor3<T1, T2, T3, ? extends T> constructor,
		Key<T1> dependency1, Key<T2> dependency2, Key<T3> dependency3
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4> ModuleBuilder1<T> to(
		TupleConstructor4<T1, T2, T3, T4, ? extends T> constructor,
		Key<T1> dependency1, Key<T2> dependency2, Key<T3> dependency3, Key<T4> dependency4
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4, T5> ModuleBuilder1<T> to(
		TupleConstructor5<T1, T2, T3, T4, T5, ? extends T> constructor,
		Key<T1> dependency1, Key<T2> dependency2, Key<T3> dependency3, Key<T4> dependency4, Key<T5> dependency5
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4, dependency5));
	}

	/**
	 * DSL shortcut for creating a binding and {@link #to(Binding) binding it} to a current key.
	 */
	default <T1, T2, T3, T4, T5, T6> ModuleBuilder1<T> to(
		TupleConstructor6<T1, T2, T3, T4, T5, T6, ? extends T> constructor,
		Key<T1> dependency1, Key<T2> dependency2, Key<T3> dependency3, Key<T4> dependency4, Key<T5> dependency5,
		Key<T6> dependency6
	) {
		return to(Binding.to(constructor, dependency1, dependency2, dependency3, dependency4, dependency5, dependency6));
	}

	/**
	 * DSL shortcut for creating a binding out of Java's constructor.
	 */
	default ModuleBuilder1<T> to(Constructor<T> constructor) {
		return to(ReflectionUtils.bindingFromConstructor(Key.of(constructor.getDeclaringClass()), constructor));
	}
}
