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
 * Unit tests to ensure the correct operation of {@link OAuth2AccessTokenRequest}.
 */
public class OAuth2AccessTokenRequestTest extends AccessTokenQueryTest<OAuth2AccessTokenRequest> {

	/** The client identifier to use in tests. */
	private static final String CLIENT_ID = "foo";

	/** The client secret to use in tests. */
	private static final String CLIENT_SECRET = "bar";

	/** The authorisation code to use in tests. */
	private static final String AUTHORISATION_CODE = "abc123";

	/**
	 * No-arg constructor.
	 */
	public OAuth2AccessTokenRequestTest() {
		super(CLIENT_ID,
				CLIENT_SECRET,
				OAuth2AccessTokenRequest.GRANT_TYPE,
				OAuth2AccessTokenRequest.CODE_PARAMETER_NAME,
				AUTHORISATION_CODE);
	}

	@Override
	protected OAuth2AccessTokenRequest newQueryInstance() {
		return new OAuth2AccessTokenRequest(CLIENT_ID, CLIENT_SECRET, AUTHORISATION_CODE);
	}
}
