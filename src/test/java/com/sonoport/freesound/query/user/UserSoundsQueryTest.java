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

import com.sonoport.freesound.query.SoundPagingQueryTest;

/**
 * Unit tests to ensure that {@link UserSoundsQuery} operates correctly.
 */
public class UserSoundsQueryTest extends SoundPagingQueryTest<UserSoundsQuery> {

	/** Username of use in tests. */
	private static final String USERNAME = "foobar";

	/**
	 * Ensure that {@link UserSoundsQuery} objects are correctly created.
	 */
	@Test
	public void queryObjectCorrectlyConstructed() {
		final UserSoundsQuery userSoundsQuery = new UserSoundsQuery(USERNAME);

		assertTrue(userSoundsQuery.getRouteParameters().size() == 1);
		assertEquals(USERNAME, userSoundsQuery.getRouteParameters().get(UserSoundsQuery.USERNAME_ROUTE_PARAMETER));
	}

	@Override
	protected UserSoundsQuery newQueryInstance() {
		return new UserSoundsQuery(USERNAME);
	}

}
