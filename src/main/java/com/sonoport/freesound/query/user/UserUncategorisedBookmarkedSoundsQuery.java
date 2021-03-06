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

/**
 * Convenience class, based on {@link UserBookmarkCategorySoundsQuery}, the retrieves all bookmarked sounds that have
 * not yet been categorised by the given user.
 */
public final class UserUncategorisedBookmarkedSoundsQuery extends UserBookmarkCategorySoundsQuery {

	/** Category id indicating 'uncategorised'. */
	protected static final int UNCATEGORISED_CATEGORY_ID = 0;

	/**
	 * @param username Username to retrieve sounds for
	 */
	public UserUncategorisedBookmarkedSoundsQuery(final String username) {
		super(username, UNCATEGORISED_CATEGORY_ID);
	}
}
