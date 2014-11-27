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

import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.SoundPagingQuery;

/**
 * Query used to retrieve list of all sounds bookmarked under a particular category by a given user.
 *
 * API documentation at: http://www.freesound.org/docs/api/resources_apiv2.html#user-bookmark-category-sounds
 */
public class UserBookmarkCategorySoundsQuery extends SoundPagingQuery<UserBookmarkCategorySoundsQuery> {

	/** Route parameter to be replaced with user name of user to retrieve sounds for. */
	protected static final String USERNAME_ROUTE_PARAMETER = "username";

	/** Route parameter to be replaced with category id to retrieve sounds for. */
	protected static final String BOOKMARK_CATEGORY_ID_ROUTE_PARAMETER = "bookmark_category_id";

	/** Path to API endpoint. */
	private static final String PATH =
			String.format(
					"/users/{%s}/bookmark_categories/{%s}/sounds/",
					USERNAME_ROUTE_PARAMETER,
					BOOKMARK_CATEGORY_ID_ROUTE_PARAMETER);

	/** The username of the user to retrieve sounds in category for. */
	private final String username;

	/** Identifier of the bookmark category to retrieve sounds for. */
	private final int bookmarkCategoryId;

	/**
	 * @param username Username of the user to retrieve sounds in category for
	 * @param bookmarkCategoryId Identifier of the bookmark category to retrieve sounds for
	 */
	public UserBookmarkCategorySoundsQuery(final String username, final int bookmarkCategoryId) {
		super(HTTPRequestMethod.GET, PATH);
		this.username = username;
		this.bookmarkCategoryId = bookmarkCategoryId;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(USERNAME_ROUTE_PARAMETER, username);
		routeParams.put(BOOKMARK_CATEGORY_ID_ROUTE_PARAMETER, String.valueOf(bookmarkCategoryId));

		return routeParams;
	}

}
