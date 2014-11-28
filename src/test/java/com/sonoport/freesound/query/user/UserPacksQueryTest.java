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

import org.junit.Test;

import com.sonoport.freesound.query.PagingQueryTest;
import com.sonoport.freesound.response.Pack;

/**
 * Unit tests to ensure the correct operation of {@link UserPacksQuery}.
 */
public class UserPacksQueryTest extends PagingQueryTest<Pack, UserPacksQuery> {

	/** User name to use in tests. */
	private static final String USERNAME = "foobar";

	/**
	 * Ensure that {@link UserPacksQuery} objects are correctly constructed.
	 */
	@Test
	public void userPacksQueryCorrectlyCreated() {
		final UserPacksQuery query = new UserPacksQuery(USERNAME);

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(USERNAME, query.getRouteParameters().get(UserPacksQuery.USERNAME_ROUTE_PARAM));
	}

	@Override
	protected UserPacksQuery newQueryInstance() {
		return new UserPacksQuery(USERNAME);
	}
}
