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

import org.json.JSONObject;

import com.sonoport.freesound.response.AccessTokenDetails;

/**
 * {@link Mapper} implementation used to convert response from OAuth2 token endpoint into an {@link AccessTokenDetails}
 * DTO.
 */
public class AccessTokenDetailsMapper extends Mapper<JSONObject, AccessTokenDetails> {

	@Override
	public AccessTokenDetails map(final JSONObject source) {
		final AccessTokenDetails accessTokenDetails = new AccessTokenDetails();

		accessTokenDetails.setAccessToken(extractFieldValue(source, "access_token", String.class));
		accessTokenDetails.setScope(extractFieldValue(source, "scope", String.class));
		accessTokenDetails.setExpiresIn(extractFieldValue(source, "expires_in", Integer.class));
		accessTokenDetails.setRefreshToken(extractFieldValue(source, "refresh_token", String.class));

		return accessTokenDetails;
	}

}
