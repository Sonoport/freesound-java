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
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.response.Pack;
import com.sonoport.freesound.response.mapping.PackMapper;
import com.sonoport.freesound.response.mapping.PagingResponseMapper;

/**
 * Query used to retrieve all {@link Pack}s belonging to a given user.
 */
public class UserPacksQuery extends PagingQuery<UserPacksQuery, Pack> {

	/** Parameter to replace on path with username of pack owner. */
	protected static final String USERNAME_ROUTE_PARAM = "username";

	/** Path to API endpoint. */
	protected static final String PATH = String.format("/users/{%s}/packs/", USERNAME_ROUTE_PARAM);

	/** The username of the user to retrieve packs for. */
	private final String username;

	/**
	 * @param username Username of the user to retrieve packs for
	 */
	public UserPacksQuery(final String username) {
		super(HTTPRequestMethod.GET, PATH, new PagingResponseMapper<>(new PackMapper()));
		this.username = username;
	}

	@Override
	protected Map<String, Object> getRequestParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(USERNAME_ROUTE_PARAM, username);

		return routeParams;
	}

}
