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

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.sonoport.freesound.response.Sound;

/**
 * Unit tests to ensure the common code within {@link SoundPagingQuery} is operating correctly.
 *
 * @param <T> The subclass of {@link SoundPagingQuery} under test
 */
public abstract class SoundPagingQueryTest<T extends SoundPagingQuery<T>> extends PagingQueryTest<Sound, T> {

	/** Field name to use when specifying values to return. */
	private static final String FIELD_1 = "id";

	/** Field name to use when specifying values to return. */
	private static final String FIELD_2 = "name";

	/**
	 * Ensure that specifying fields individually using the FLuent API works correctly.
	 */
	@Test
	public void specifyIndividualFieldsUsingFluentAPI() {
		final T query = newQueryInstance();
		query.includeField(FIELD_1).includeField(FIELD_2);

		final String fieldsList = (String) query.getQueryParameters().get(SoundPagingQuery.FIELDS_PARAMETER);
		final List<String> fieldsParameterValues = Arrays.asList(fieldsList.split(","));

		assertTrue(fieldsParameterValues.size() == 2);
		assertTrue(fieldsParameterValues.contains(FIELD_1));
		assertTrue(fieldsParameterValues.contains(FIELD_2));
	}

	/**
	 * Ensure that specifying fields as a batch using the Fluent API works correctly.
	 */
	@Test
	public void specifyCollectionOfFieldsUsingFluentAPI() {
		final Set<String> fields = new HashSet<>(Arrays.asList(FIELD_1, FIELD_2));

		final T query = newQueryInstance();
		query.includeFields(fields);

		final String fieldsList = (String) query.getQueryParameters().get(SoundPagingQuery.FIELDS_PARAMETER);
		final List<String> fieldsParameterValues = Arrays.asList(fieldsList.split(","));

		assertTrue(fieldsParameterValues.size() == 2);
		assertTrue(fieldsParameterValues.contains(FIELD_1));
		assertTrue(fieldsParameterValues.contains(FIELD_2));
	}

}
