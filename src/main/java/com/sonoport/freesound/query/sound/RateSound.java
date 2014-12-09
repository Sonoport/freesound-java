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
package com.sonoport.freesound.query.sound;

import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.OAuthQuery;
import com.sonoport.freesound.response.mapping.DetailStringMapper;

/**
 * Query class to allow users to rate sounds on the freesound.org platform.
 */
public class RateSound extends JSONResponseQuery<String> implements OAuthQuery {

	/** Name of route parameter to be replaced with the sound identifier. */
	protected static final String SOUND_IDENTIFIER_ROUTE_PARAMETER = "sound_id";

	/** Parameter name to pass the rating across as. */
	protected static final String RATING_PARAMETER_NAME = "rating";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/sounds/{%s}/rate/", SOUND_IDENTIFIER_ROUTE_PARAMETER);

	/** Identifier of sound to add comment to. */
	private final int soundIdentifier;

	/** Rating to give the sound. */
	private final int rating;

	/** OAuth taken to present. */
	private final String oauthToken;

	/**
	 * @param soundIdentifier Identifier of sound
	 * @param rating Rating to apply
	 * @param oauthToken OAuth2 token to present
	 */
	public RateSound(final int soundIdentifier, final int rating, final String oauthToken) {
		super(HTTPRequestMethod.POST, PATH, new DetailStringMapper());
		this.soundIdentifier = soundIdentifier;
		this.oauthToken = oauthToken;

		// Check that the rating falls between acceptable bounds (0-5, inclusive)
		if ((rating < 0) || (rating > 5)) {
			throw new IllegalArgumentException("Ratings must be between 0 and 5 (inclusive)");
		} else {
			this.rating = rating;
		}
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_IDENTIFIER_ROUTE_PARAMETER, String.valueOf(soundIdentifier));

		return routeParams;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(RATING_PARAMETER_NAME, String.valueOf(rating));

		return queryParams;
	}

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
