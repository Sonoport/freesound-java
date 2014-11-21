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

import org.json.JSONObject;

import com.sonoport.freesound.response.mapping.Mapper;

/**
 * Abstract extension of the {@link Query} class to represent queries that require OAuth2 credentials.
 *
 * @param <R> The data type of the results to return
 */
public abstract class OAuthQuery<R extends Object> extends Query<R> {

	/** The OAuth2 token to present with the query. */
	private final String oauthToken;

	/**
	 * @param httpRequestMethod The HTTP method to use in the call
	 * @param path The path to the API endpoint
	 * @param resultsMapper {@link Mapper} class to convert results
	 * @param oauthToken OAuth token to present as credentials
	 */
	protected OAuthQuery(
			final HTTPRequestMethod httpRequestMethod,
			final String path,
			final Mapper<JSONObject, R> resultsMapper,
			final String oauthToken) {
		super(httpRequestMethod, path, resultsMapper);
		this.oauthToken = oauthToken;
	}

	/**
	 * @return the oauthToken
	 */
	public String getOauthToken() {
		return oauthToken;
	}

}
