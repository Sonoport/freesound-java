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
package com.sonoport.freesound.query.sound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link BookmarkSound}.
 */
public class BookmarkSoundTest extends JSONResponseQueryTest<BookmarkSound> {

	/** Sound identifier to use in tests. */
	private static final int SOUND_ID = 1234;

	/** OAuth token to use in tests. */
	private static final String OAUTH_TOKEN = "abc12345def";

	/** Bookmark name to use in tests. */
	private static final String BOOKMARK_NAME = "bookmark-name";

	/** Bookmark category to use in tests. */
	private static final String BOOKMARK_CATEGORY = "category";

	/**
	 * Ensure that instances of {@link BookmarkSound} are constructed correctly.
	 */
	@Test
	public void bookmarkSoundQueryCorrectlyConstructed() {
		final BookmarkSound query = newQueryInstance();

		assertEquals(OAUTH_TOKEN, query.getOauthToken());

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(String.valueOf(SOUND_ID), query.getRouteParameters().get(BookmarkSound.SOUND_ID_ROUTE_PARAMETER));

		// Ensure query parameters are not included if they haven't been set
		assertFalse(query.getQueryParameters().containsKey(BookmarkSound.BOOKMARK_NAME_PARAMETER));
		assertFalse(query.getQueryParameters().containsKey(BookmarkSound.BOOKMARK_CATEGORY_PARAMETER));
	}

	/**
	 * Ensure that {@link BookmarkSound} objects are correctly created when using the 'full' constructor.
	 */
	@Test
	public void fullDetailsBookmarkSoundQueryCorrectlyConstructed() {
		final BookmarkSound query = new BookmarkSound(SOUND_ID, OAUTH_TOKEN, BOOKMARK_NAME, BOOKMARK_CATEGORY);

		assertEquals(OAUTH_TOKEN, query.getOauthToken());

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(String.valueOf(SOUND_ID), query.getRouteParameters().get(BookmarkSound.SOUND_ID_ROUTE_PARAMETER));

		final Map<String, Object> queryParameters = query.getQueryParameters();
		assertEquals(BOOKMARK_NAME, queryParameters.get(BookmarkSound.BOOKMARK_NAME_PARAMETER));
		assertEquals(BOOKMARK_CATEGORY, queryParameters.get(BookmarkSound.BOOKMARK_CATEGORY_PARAMETER));
	}

	/**
	 * Ensure that use of the Fluent API methods correctly populates values in query parameters.
	 */
	@Test
	public void fluentAPICorrectlyPopulatesQueryParameters() {
		final BookmarkSound bookmark = newQueryInstance().name(BOOKMARK_NAME).category(BOOKMARK_CATEGORY);

		final Map<String, Object> queryParameters = bookmark.getQueryParameters();
		assertEquals(BOOKMARK_NAME, queryParameters.get(BookmarkSound.BOOKMARK_NAME_PARAMETER));
		assertEquals(BOOKMARK_CATEGORY, queryParameters.get(BookmarkSound.BOOKMARK_CATEGORY_PARAMETER));
	}

	@Override
	protected BookmarkSound newQueryInstance() {
		return new BookmarkSound(SOUND_ID, OAUTH_TOKEN);
	}
}
