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
 * Abstract class used to map the common elements of user details from freesound to a specific subclass.
 *
 * @param <R> The type to map user details to
 */
public abstract class AbstractUserMapper<R extends User> extends Mapper<JSONObject, R> {

	/** The type to map user details to. */
	private final Class<R> returnType;

	/**
	 * @param returnType Type to map user details to
	 */
	protected AbstractUserMapper(final Class<R> returnType) {
		this.returnType = returnType;
	}

	@Override
	public R map(final JSONObject source) {
		R user = null;
		try {
			user = returnType.newInstance();

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
		} catch (final InstantiationException e) {
			e.printStackTrace();
		} catch (final IllegalAccessException e) {
			e.printStackTrace();
		}

		return user;
	}


}
