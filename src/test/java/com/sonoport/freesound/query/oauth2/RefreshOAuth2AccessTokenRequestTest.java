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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of {@link RefreshOAuth2AccessTokenRequest}.
 */
public class RefreshOAuth2AccessTokenRequestTest {

	/** The client identifier to use in tests. */
	private static final String CLIENT_ID = "foo";

	/** The client secret to use in tests. */
	private static final String CLIENT_SECRET = "bar";

	/** The refresh token to use in tests. */
	private static final String REFRESH_TOKEN = "abc123";

	/**
	 * Ensure that {@link RefreshOAuth2AccessTokenRequest}s are constructed correctly.
	 */
	@Test
	public void refreshTokeRequestCorrectlyConstructed() {
		final RefreshOAuth2AccessTokenRequest request =
				new RefreshOAuth2AccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN);

		assertNotNull(request.getRouteParameters());
		assertTrue(request.getRouteParameters().isEmpty());

		final Map<String, Object> queryParameters = request.getQueryParameters();
		assertEquals(CLIENT_ID, queryParameters.get(AccessTokenQuery.CLIENT_ID_PARAMETER_NAME));
		assertEquals(CLIENT_SECRET, queryParameters.get(AccessTokenQuery.CLIENT_SECRET_PARAMETER_NAME));
		assertEquals(
				RefreshOAuth2AccessTokenRequest.GRANT_TYPE,
				queryParameters.get(AccessTokenQuery.GRANT_TYPE_PARAMETER_NAME));
		assertEquals(REFRESH_TOKEN, queryParameters.get(RefreshOAuth2AccessTokenRequest.CODE_PARAMETER_NAME));
	}
}
