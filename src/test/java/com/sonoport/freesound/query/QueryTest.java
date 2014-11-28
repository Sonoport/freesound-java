/*
 * Copyright 2014 Sonoport (Asia) Pte Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sonoport.freesound.query;

/**
 * Top level class for creating tests for {@link Query} classes.
 *
 * @param <T> {@link Query} class under test
 */
public abstract class QueryTest<T extends Query<?, ?>> {

	/**
	 * Build and return a simple instance of the query under test.
	 *
	 * @return Instance of query under test
	 */
	protected abstract T newQueryInstance();
}
