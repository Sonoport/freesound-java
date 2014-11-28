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

import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.OAuthQuery;
import com.sonoport.freesound.response.mapping.DetailStringMapper;

/**
 * Class that allows the currently authorised user to bookmark a sound. By default the bookmark will use the name of the
 * sound and be uncategorised - this can be overridden by using the {@link #name(String)} and {@link #category(String)}
 * methods.
 *
 * API documentation: http://www.freesound.org/docs/api/resources_apiv2.html#bookmark-sound-oauth2-required
 *
 * Note that there is currently a bug in the Freesound API that means both name and category of the bookmark must be
 * specified at present (despite the documentation stating they are optional). A bug has been raised on GitHub covering
 * this issue: https://github.com/MTG/freesound/issues/642.
 */
public class BookmarkSound extends JSONResponseQuery<String> implements OAuthQuery {

	/** Route parameter to be replaced with the identifier of the sound to be bookmarked. */
	protected static final String SOUND_ID_ROUTE_PARAMETER = "sound_id";

	/** Path to the API endpoint. */
	private static final String PATH = String.format("/sounds/{%s}/bookmark/", SOUND_ID_ROUTE_PARAMETER);

	/** Parameter to pass the (optional) name of the bookmark on as. */
	protected static final String BOOKMARK_NAME_PARAMETER = "name";

	/** Parameter to pass the (optional) category of the bookmark on as. */
	protected static final String BOOKMARK_CATEGORY_PARAMETER = "category";

	/** Identifier of the sound to bookmark. */
	private final int soundId;

	/** Name to give the bookmark. */
	private String bookmarkName;

	/** Category to group the bookmark under. */
	private String bookmarkCategory;

	/** OAuth2 bearer token to present with request. */
	private final String oauthToken;

	/**
	 * @param soundId Identifier of sound to bookmark
	 * @param oauthToken OAuth token to present with request
	 */
	public BookmarkSound(final int soundId, final String oauthToken) {
		this(soundId, oauthToken, null, null);
	}

	/**
	 * @param soundId Identifier of sound to bookmark
	 * @param oauthToken OAuth token to present with request
	 * @param bookmarkName Name to give the bookmark
	 * @param bookmarkCategory Category to assign the bookmark to
	 */
	public BookmarkSound(
			final int soundId, final String oauthToken, final String bookmarkName, final String bookmarkCategory) {
		super(HTTPRequestMethod.POST, PATH, new DetailStringMapper());
		this.soundId = soundId;
		this.oauthToken = oauthToken;
		this.bookmarkName = bookmarkName;
		this.bookmarkCategory = bookmarkCategory;
	}

	/**
	 * Specify the name to give the bookmark.
	 *
	 * @param bookmarkName Name of the bookmark
	 * @return Current {@link BookmarkSound} object
	 */
	public BookmarkSound name(final String bookmarkName) {
		this.bookmarkName = bookmarkName;
		return this;
	}

	/**
	 * Specify the category to group the bookmark under. If the category does not exist it will be created.
	 *
	 * @param bookmarkCategory Name of category
	 * @return Current {@link BookmarkSound} object
	 */
	public BookmarkSound category(final String bookmarkCategory) {
		this.bookmarkCategory = bookmarkCategory;
		return this;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_ID_ROUTE_PARAMETER, String.valueOf(soundId));

		return routeParams;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();

		if (bookmarkName != null) {
			queryParams.put(BOOKMARK_NAME_PARAMETER, bookmarkName);
		}

		if (bookmarkCategory != null) {
			queryParams.put(BOOKMARK_CATEGORY_PARAMETER, bookmarkCategory);
		}

		return queryParams;
	}

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
