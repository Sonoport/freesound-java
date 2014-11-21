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

import com.sonoport.freesound.response.CurrentUser;

/**
 * Map the freesound.org JSON representation of the currently authorised user to a {@link CurrentUser} DTO.
 */
public class CurrentUserMapper extends AbstractUserMapper<CurrentUser> {

	/**
	 * No-arg constructor.
	 */
	public CurrentUserMapper() {
		super(CurrentUser.class);
	}

	@Override
	public CurrentUser map(final JSONObject source) {
		final CurrentUser currentUser = super.map(source);

		currentUser.setEmail(extractFieldValue(source, "email", String.class));
		currentUser.setUniqueIdentifier(extractFieldValue(source, "unique_id", Integer.class));

		return currentUser;
	}

}
