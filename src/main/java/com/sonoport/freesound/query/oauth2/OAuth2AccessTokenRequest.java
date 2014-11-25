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


/**
 * Query used to request an OAuth2 access token based on an authorisation code previously returned.
 */
public class OAuth2AccessTokenRequest extends AccessTokenQuery {

	/** The grant type to request from the OAuth2 endpoint. */
	protected static final String GRANT_TYPE = "authorization_code";

	/** The name of the parameter to pass the authorisation code through as. */
	protected static final String CODE_PARAMETER_NAME = "code";

	/**
	 * @param clientId Application client id
	 * @param clientSecret Application client secret
	 * @param authorisationCode Authorisation code previously received
	 */
	public OAuth2AccessTokenRequest(final String clientId, final String clientSecret, final String authorisationCode) {
		super(clientId, clientSecret, GRANT_TYPE, CODE_PARAMETER_NAME, authorisationCode);
	}

}
