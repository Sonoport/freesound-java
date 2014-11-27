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

import java.util.Map;

import org.junit.Test;

import com.sonoport.freesound.query.SoundPagingQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link UserUncategorisedBookmarkedSoundsQuery}.
 */
public class UserUncategorisedBookmarkedSoundsQueryTest
			extends SoundPagingQueryTest<UserBookmarkCategorySoundsQuery> {

	/** Username to use in tests. */
	private static final String USERNAME = "foobar";

	/**
	 * Ensure that {@link UserUncategorisedBookmarkedSoundsQuery} instances are correctly constructed.
	 */
	@Test
	public void queryCorrectlyConstructed() {
		final UserUncategorisedBookmarkedSoundsQuery query = new UserUncategorisedBookmarkedSoundsQuery(USERNAME);

		final Map<String, String> routeParameters = query.getRouteParameters();
		assertTrue(routeParameters.size() == 2);
		assertEquals(USERNAME, routeParameters.get(UserBookmarkCategorySoundsQuery.USERNAME_ROUTE_PARAMETER));
		assertEquals(
				String.valueOf(UserUncategorisedBookmarkedSoundsQuery.UNCATEGORISED_CATEGORY_ID),
				routeParameters.get(UserBookmarkCategorySoundsQuery.BOOKMARK_CATEGORY_ID_ROUTE_PARAMETER));
	}

	@Override
	protected UserUncategorisedBookmarkedSoundsQuery newQueryInstance() {
		return new UserUncategorisedBookmarkedSoundsQuery(USERNAME);
	}

}
