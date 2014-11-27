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
package com.sonoport.freesound.query.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.sonoport.freesound.query.SoundPagingQuery;

/**
 * Unit tests to ensure that {@link UserSoundsQuery} operates correctly.
 */
public class UserSoundsQueryTest {

	/** Username of use in tests. */
	private static final String USERNAME = "foobar";

	/** Field name to use when specifying values to return. */
	private static final String FIELD_1 = "id";

	/** Field name to use when specifying values to return. */
	private static final String FIELD_2 = "name";

	/**
	 * Ensure that {@link UserSoundsQuery} objects are correctly created.
	 */
	@Test
	public void queryObjectCorrectlyConstructed() {
		final UserSoundsQuery userSoundsQuery =
				new UserSoundsQuery(USERNAME).includeField(FIELD_1).includeField(FIELD_2);

		assertTrue(userSoundsQuery.getRouteParameters().size() == 1);
		assertEquals(USERNAME, userSoundsQuery.getRouteParameters().get(UserSoundsQuery.USERNAME_ROUTE_PARAMETER));

		final String fieldsList = (String) userSoundsQuery.getQueryParameters().get(SoundPagingQuery.FIELDS_PARAMETER);
		final List<String> fieldsParameterValues = Arrays.asList(fieldsList.split(","));

		assertTrue(fieldsParameterValues.size() == 2);
		assertTrue(fieldsParameterValues.contains(FIELD_1));
		assertTrue(fieldsParameterValues.contains(FIELD_2));
	}

}
