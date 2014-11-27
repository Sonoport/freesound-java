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
import com.sonoport.freesound.response.BookmarkCategory;

/**
 * Unit tests to ensure the correct operation of {@link UserBookmarkCategoriesQuery}.
 */
public class UserBookmarkCategoriesQueryTest extends PagingQueryTest<BookmarkCategory, UserBookmarkCategoriesQuery> {

	/** User name to use in tests. */
	private static final String USERNAME = "foo";

	/**
	 * Ensure that {@link UserBookmarkCategoriesQuery} objects are correctly constructed.
	 */
	@Test
	public void userBookmarkCategoriesQueryCreatedCorrectly() {
		final UserBookmarkCategoriesQuery query = new UserBookmarkCategoriesQuery(USERNAME);

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(USERNAME, query.getRouteParameters().get(UserBookmarkCategoriesQuery.USERNAME_ROUTE_PARAMETER));
	}

	@Override
	protected UserBookmarkCategoriesQuery newQueryInstance() {
		return new UserBookmarkCategoriesQuery(USERNAME);
	}
}
