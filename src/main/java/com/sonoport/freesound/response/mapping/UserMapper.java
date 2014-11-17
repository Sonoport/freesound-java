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

import com.sonoport.freesound.response.User;

/**
 * Map the freesound.org JSON representation of an individual user instance to a {@link User} DTO.
 */
public class UserMapper extends Mapper<JSONObject, User> {

	@Override
	public User map(final JSONObject source) {
		final User user = new User();

		user.setUrl(extractFieldValue(source, "url", String.class));
		user.setUsername(extractFieldValue(source, "username", String.class));
		user.setAbout(extractFieldValue(source, "about", String.class));
		user.setHomepage(extractFieldValue(source, "home_page", String.class));
		user.setAvatarURIs(parseDictionary(extractFieldValue(source, "avatar", JSONObject.class)));
		user.setDateJoined(parseDate(extractFieldValue(source, "date_joined", String.class)));
		user.setNumberOfSounds(extractFieldValue(source, "num_sounds", Integer.class));
		user.setSoundsURI(extractFieldValue(source, "sounds", String.class));
		user.setNumberOfPacks(extractFieldValue(source, "num_packs", Integer.class));
		user.setPacksURI(extractFieldValue(source, "packs", String.class));
		user.setNumberOfPosts(extractFieldValue(source, "num_posts", Integer.class));
		user.setNumberOfComments(extractFieldValue(source, "num_comments", Integer.class));
		user.setBookmarkCategoriesURI(extractFieldValue(source, "bookmark_categories", String.class));

		return user;
	}

}
