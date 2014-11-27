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
 * Unit tests to ensure the correct operation of {@link UserBookmarkCategorySoundsQuery}.
 */
public class UserBookmarkCategorySoundsQueryTest extends SoundPagingQueryTest<UserBookmarkCategorySoundsQuery> {

	/** Username to use in tests. */
	private static final String USERNAME = "foo";

	/** Bookmark category identifier to use in tests. */
	private static final int BOOKMARK_CATEGORY_ID = 54321;

	/**
	 * Ensure that the route parameters are correctly populated by {@link UserBookmarkCategorySoundsQuery}.
	 */
	@Test
	public void checkRouteParametersCorrectlyPopulated() {
		final UserBookmarkCategorySoundsQuery query = newQueryInstance();

		assertTrue(query.getRouteParameters().size() == 2);
		assertEquals(
				USERNAME, query.getRouteParameters().get(UserBookmarkCategorySoundsQuery.USERNAME_ROUTE_PARAMETER));
		assertEquals(
				String.valueOf(BOOKMARK_CATEGORY_ID),
				query.getRouteParameters().get(UserBookmarkCategorySoundsQuery.BOOKMARK_CATEGORY_ID_ROUTE_PARAMETER));
	}

	@Override
	protected UserBookmarkCategorySoundsQuery newQueryInstance() {
		return new UserBookmarkCategorySoundsQuery(USERNAME, BOOKMARK_CATEGORY_ID);
	}

}
