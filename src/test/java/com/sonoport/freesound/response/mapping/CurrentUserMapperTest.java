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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.CurrentUser;

/**
 * Unit tests to ensure the correct operation of {@link CurrentUserMapper}.
 */
public class CurrentUserMapperTest extends MapperTest {

	/** User URL. */
	private static final String URL = "http://www.freesound.org/people/wluna/";

	/** Username. */
	private static final String USERNAME = "wluna";

	/** User 'about' details. */
	private static final String ABOUT =
			"Hello, my name is Wilmar.\r\n\r\nI am the author of a sci-fi superheroine book called The Silver Ninja and am also a professional motion graphics animator and video editor.\r\n\r\nI love video games, films, and art. Please feel free to check out my book at thesilverninja.com, thanks!";

	/** User's home page. */
	private static final String HOME_PAGE = "http://www.thesilverninja.com/";

	/** Collection of URIs to user's avatars. */
	private static final Map<String, String> AVATAR_URIS;
	static {
		AVATAR_URIS = new HashMap<String, String>();
		AVATAR_URIS.put("small", "http://www.freesound.org/data/avatars/4038/4038431_S.jpg");
		AVATAR_URIS.put("medium", "http://www.freesound.org/data/avatars/4038/4038431_M.jpg");
		AVATAR_URIS.put("large", "http://www.freesound.org/data/avatars/4038/4038431_L.jpg");
	};

    /** Date user joined freesound. Long value is the millisecond-since-epoch representation of the String
     * "2014-01-28T01:04:15.047" presented by freesound. */
    private static final Date DATE_JOINED = new Date(1390871055000L);

    /** Number of sounds belonging to user. */
    private static final int NUMBER_OF_SOUNDS = 6;

    /** URI of user's sounds. */
    private static final String SOUNDS_URI = "http://www.freesound.org/apiv2/users/wluna/sounds/";

    /** Number of packs belonging to user. */
    private static final int NUMBER_OF_PACKS = 1;

    /** URI to user;s packs. */
    private static final String PACKS_URI = "http://www.freesound.org/apiv2/users/wluna/packs/";

    /** Number of forum posts. */
    private static final int NUMBER_OF_POSTS = 0;

    /** Number of comments. */
    private static final int NUMBER_OF_COMMENTS = 2;

    /** URI to user's bookmark categories. */
    private static final String BOOKMARK_CATEGORIES_URI =
    		"http://www.freesound.org/apiv2/users/wluna/bookmark_categories/";

    /** Email associated with the user account. */
    private static final String EMAIL = "email@random.com";

    /** Unique identifier associated with the user account. */
    private static final int UNIQUE_ID = 1234567;

	/** Instance of {@link CurrentUserMapper} to use in tests. */
	private final CurrentUserMapper mapper = new CurrentUserMapper();

	/**
	 * Ensure {@link CurrentUserMapper} correctly processes a valid current user record.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseCurrentUserDetails() throws Exception {
		final JSONObject currentUserJSON = readJSONFile("/current-user.json");

		final CurrentUser currentUser = mapper.map(currentUserJSON);

		assertEquals(URL, currentUser.getUrl());
		assertEquals(USERNAME, currentUser.getUsername());
		assertEquals(ABOUT, currentUser.getAbout());
		assertEquals(HOME_PAGE, currentUser.getHomepage());
		compareMaps(AVATAR_URIS, currentUser.getAvatarURIs());
		assertEquals(DATE_JOINED, currentUser.getDateJoined());
		assertEquals(Integer.valueOf(NUMBER_OF_SOUNDS), currentUser.getNumberOfSounds());
		assertEquals(SOUNDS_URI, currentUser.getSoundsURI());
		assertEquals(Integer.valueOf(NUMBER_OF_PACKS), currentUser.getNumberOfPacks());
		assertEquals(PACKS_URI, currentUser.getPacksURI());
		assertEquals(Integer.valueOf(NUMBER_OF_POSTS), currentUser.getNumberOfPosts());
		assertEquals(Integer.valueOf(NUMBER_OF_COMMENTS), currentUser.getNumberOfComments());
		assertEquals(BOOKMARK_CATEGORIES_URI, currentUser.getBookmarkCategoriesURI());
		assertEquals(EMAIL, currentUser.getEmail());
		assertEquals(Integer.valueOf(UNIQUE_ID), currentUser.getUniqueIdentifier());
	}
}
