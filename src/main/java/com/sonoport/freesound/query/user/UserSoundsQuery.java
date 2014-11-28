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
 * Query used to retrieve all the sounds owned by a particular user.
 */
public class UserSoundsQuery extends SoundPagingQuery<UserSoundsQuery> {

	/** Name of the parameter in the path to replace with the username of owner of the sounds to retrieve. */
	protected static final String USERNAME_ROUTE_PARAMETER = "username";

	/** Path to the API endpoint. */
	private static final String PATH = String.format("/users/{%s}/sounds/", USERNAME_ROUTE_PARAMETER);

	/** The username of the owner of the sounds to retrieve. */
	private final String username;

	/**
	 * @param username Username of the owner of the sounds to retrieve
	 */
	public UserSoundsQuery(final String username) {
		super(HTTPRequestMethod.GET, PATH);
		this.username = username;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(USERNAME_ROUTE_PARAMETER, username);

		return routeParams;
	}

}
