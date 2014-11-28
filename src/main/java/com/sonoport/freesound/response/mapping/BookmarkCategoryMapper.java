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

import org.json.JSONObject;

import com.sonoport.freesound.response.BookmarkCategory;

/**
 * {@link Mapper} class used to convert JSON representation of a bookmark category into a {@link BookmarkCategory} DTO.
 */
public class BookmarkCategoryMapper extends Mapper<JSONObject, BookmarkCategory> {

	@Override
	public BookmarkCategory map(final JSONObject source) {
		final BookmarkCategory bookmarkCategory = new BookmarkCategory();

		bookmarkCategory.setUrl(extractFieldValue(source, "url", String.class));
		bookmarkCategory.setName(extractFieldValue(source, "name", String.class));
		bookmarkCategory.setNumberOfSounds(extractFieldValue(source, "num_sounds", Integer.class));
		bookmarkCategory.setSoundsURL(extractFieldValue(source, "sounds", String.class));

		return bookmarkCategory;
	}

}
