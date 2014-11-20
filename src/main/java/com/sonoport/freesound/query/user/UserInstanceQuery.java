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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.Query;
import com.sonoport.freesound.response.User;
import com.sonoport.freesound.response.mapping.UserMapper;

/**
 * Class used to represent a request for details regarding a single user instance. The user to retrieve is identified by
 * their username.
 *
 * http://www.freesound.org/docs/api/resources_apiv2.html#user-instance
 */
public class UserInstanceQuery extends Query<User> {

	/** Route parameter containing username of user resource to retrieve. */
	protected static final String USERNAME_PARAMETER = "username";

	/** API path to retrieve the user details from. */
	private static final String PATH = String.format("/users/{%s}", USERNAME_PARAMETER);

	/** The identifier of the user to retrieve. */
	private final String username;

	/**
	 * @param username The username to retrieve
	 */
	public UserInstanceQuery(final String username) {
		super(HTTPRequestMethod.GET, PATH, new UserMapper());

		if ((username == null) || username.trim().equals("")) {
			throw new IllegalArgumentException("No username specified");
		}

		this.username = username;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParameters = new HashMap<>();
		routeParameters.put(USERNAME_PARAMETER, username);

		return routeParameters;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

}
