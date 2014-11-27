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
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.response.BookmarkCategory;
import com.sonoport.freesound.response.mapping.BookmarkCategoryMapper;
import com.sonoport.freesound.response.mapping.PagingResponseMapper;

/**
 * Query used to retrieve the list of bookmark categories owned by a particualar user.
 */
public class UserBookmarkCategoriesQuery extends PagingQuery<UserBookmarkCategoriesQuery, BookmarkCategory> {

	/** Route parameter to replace with username. */
	protected static final String USERNAME_ROUTE_PARAMETER = "username";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/users/{%s}/bookmark_categories/", USERNAME_ROUTE_PARAMETER);

	/** Username of user to retrieve bookmark categories for. */
	private final String username;

	/**
	 * @param username Username of user to retrieve bookmark categories for
	 */
	public UserBookmarkCategoriesQuery(final String username) {
		super(HTTPRequestMethod.GET, PATH, new PagingResponseMapper<>(new BookmarkCategoryMapper()));
		this.username = username;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(USERNAME_ROUTE_PARAMETER, username);

		return routeParams;
	}

}
