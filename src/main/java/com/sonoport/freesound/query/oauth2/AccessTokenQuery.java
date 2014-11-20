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
package com.sonoport.freesound.query.oauth2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.Query;
import com.sonoport.freesound.response.AccessTokenDetails;
import com.sonoport.freesound.response.mapping.AccessTokenDetailsMapper;

/**
 * Abstract class extended by queries that make calls to the OAuth2 token endpoint on freesound.org. These classes can
 * be constructed manually, but are more easily used by calling the convenience methods in FreesoundClient to get or
 * renew access tokens.
 */
public abstract class AccessTokenQuery extends Query<AccessTokenDetails> {

	/** The Client Id, as provided by freesound. */
	private final String clientId;

	/** The Client Secret, as provided by freesound. */
	private final String clientSecret;

	/** The grant type being requested for the token. */
	private final String grantType;

	/** The name of the parameter to pass the required code as. */
	private final String codeParameterName;

	/** The code to pass. */
	private final String code;

	/**
	 * @param clientId Application client id
	 * @param clientSecret Application client secret
	 * @param grantType Grant type requested
	 * @param codeParameterName Parameter to pass code as
	 * @param code Code to pass
	 */
	protected AccessTokenQuery(
			final String clientId,
			final String clientSecret,
			final String grantType,
			final String codeParameterName,
			final String code) {
		super(HTTPRequestMethod.POST, "/oauth2/access_token", new AccessTokenDetailsMapper());
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.grantType = grantType;
		this.codeParameterName = codeParameterName;
		this.code = code;
	}

	@Override
	public final Map<String, Object> getQueryParameters() {
		final Map<String, Object> params = new HashMap<>();

		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("grant_type", grantType);
		params.put(codeParameterName, code);

		return params;
	}

	@Override
	public final Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

}
