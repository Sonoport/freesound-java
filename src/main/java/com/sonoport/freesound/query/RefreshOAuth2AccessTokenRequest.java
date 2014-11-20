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

/**
 * Query used to request a new OAuth2 access token based on a refresh token previously issued.
 */
public class RefreshOAuth2AccessTokenRequest extends AccessTokenQuery {

	/**
	 * @param clientId Application client id
	 * @param clientSecret Application client secret
	 * @param refreshToken The refresh token to present
	 */
	public RefreshOAuth2AccessTokenRequest(
			final String clientId, final String clientSecret, final String refreshToken) {
		super(clientId, clientSecret, "refresh_token", "refresh_token", refreshToken);
	}

}
