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
package com.sonoport.freesound.response.mapping;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.AccessTokenDetails;

/**
 * Unit tests to ensure that {@link AccessTokenDetailsMapper} correctly processes responses from the OAuth2 token
 * endpoint.
 *
 * Test data is located at <code>/src/test/resources/access-token.json</code>.
 */
public class AccessTokenDetailsMapperTest extends MapperTest {

	/** The access token returned. */
	private static final String ACCESS_TOKEN = "64c64660ceed813476b314f52136d9698e075622";

	/** The scope associated with the access token. */
	private static final String SCOPE = "read write read+write";

	/** Number of seconds the token is valid for. */
	private static final int EXPIRES_IN = 86399;

	/** Refresh token for generating further access tokens. */
	private static final String REFRESH_TOKEN = "0354489231f6a874331aer4927569297c7fea4d5";

	/** Instance of {@link AccessTokenDetailsMapper} to use in tests. */
	private final AccessTokenDetailsMapper mapper = new AccessTokenDetailsMapper();

	/**
	 * Ensure that a valid response is correctly parsed.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseAccessTokenDetails() throws Exception {
		final JSONObject accessTokenJSON = readJSONFile("/access-token.json");

		final AccessTokenDetails accessTokenDetails = mapper.map(accessTokenJSON);

		assertEquals(ACCESS_TOKEN, accessTokenDetails.getAccessToken());
		assertEquals(SCOPE, accessTokenDetails.getScope());
		assertEquals(EXPIRES_IN, accessTokenDetails.getExpiresIn());
		assertEquals(REFRESH_TOKEN, accessTokenDetails.getRefreshToken());
	}
}
