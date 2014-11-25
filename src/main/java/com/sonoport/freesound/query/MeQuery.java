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
package com.sonoport.freesound.query;

import java.util.Collections;
import java.util.Map;

import com.sonoport.freesound.response.CurrentUser;
import com.sonoport.freesound.response.mapping.CurrentUserMapper;

/**
 * Query used to retrieve full details of the currently authorised user.
 */
public class MeQuery extends JSONResponseQuery<CurrentUser> implements OAuthQuery {

	/** The OAuth bearer token to be presented with the request. */
	private final String oauthToken;

	/**
	 * @param oauthToken The OAuth token associated with the user
	 */
	public MeQuery(final String oauthToken) {
		super(HTTPRequestMethod.GET, "/me/", new CurrentUserMapper());
		this.oauthToken = oauthToken;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
