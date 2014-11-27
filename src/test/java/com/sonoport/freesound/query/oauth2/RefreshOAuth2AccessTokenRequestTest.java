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
 * Unit tests to ensure the correct operation of {@link RefreshOAuth2AccessTokenRequest}.
 */
public class RefreshOAuth2AccessTokenRequestTest extends AccessTokenQueryTest<RefreshOAuth2AccessTokenRequest> {

	/** The client identifier to use in tests. */
	private static final String CLIENT_ID = "foo";

	/** The client secret to use in tests. */
	private static final String CLIENT_SECRET = "bar";

	/** The refresh token to use in tests. */
	private static final String REFRESH_TOKEN = "abc123";

	/**
	 * No-arg constructor.
	 */
	public RefreshOAuth2AccessTokenRequestTest() {
		super(CLIENT_ID,
				CLIENT_SECRET,
				RefreshOAuth2AccessTokenRequest.GRANT_TYPE,
				RefreshOAuth2AccessTokenRequest.CODE_PARAMETER_NAME,
				REFRESH_TOKEN);
	}

	@Override
	protected RefreshOAuth2AccessTokenRequest newQueryInstance() {
		return new RefreshOAuth2AccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN);
	}
}
