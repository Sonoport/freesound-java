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
package com.sonoport.freesound.response.mapping;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.BookmarkCategory;

/**
 * Unit tests to ensure the correct operation of {@link BookmarkCategoryMapper}.
 */
public class BookmarkCategoryMapperTest extends MapperTest {

	/** URL to use in tests. */
	private static final String URL = "http://www.freesound.org/people/frederic.font/bookmarks/category/23840/";

	/** Name of category. */
	private static final String NAME = "Rooms";

	/** Number of sounds in category. */
	private static final int NUMBER_OF_SOUNDS = 1;

	/** URL to list of sounds in category. */
	private static final String SOUNDS_URL =
			"http://www.freesound.org/apiv2/users/frederic.font/bookmark_categories/23840/sounds/";

	/** Instance of {@link BookmarkCategoryMapper} to use in tests. */
	private final BookmarkCategoryMapper mapper = new BookmarkCategoryMapper();

	/**
	 * Ensure that a properly formatted JSON representation of a bookmark category is correctly converted into a
	 * {@link BookmarkCategory} object.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseBookmarkCategory() throws Exception {
		final JSONObject bookmarkCategoryJSON = readJSONFile("/bookmark-category.json");

		final BookmarkCategory bookmarkCategory = mapper.map(bookmarkCategoryJSON);

		assertEquals(URL, bookmarkCategory.getUrl());
		assertEquals(NAME, bookmarkCategory.getName());
		assertEquals(NUMBER_OF_SOUNDS, bookmarkCategory.getNumberOfSounds());
		assertEquals(SOUNDS_URL, bookmarkCategory.getSoundsURL());
	}
}
