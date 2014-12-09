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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link RateSound}.
 */
public class RateSoundTest extends JSONResponseQueryTest<RateSound> {

	/** Sound id to use in tests. */
	private static final int SOUND_ID = 12345;

	/** Rating to use in tests. */
	private static final int RATING = 2;

	/** Oauth token to use in tests. */
	private static final String OAUTH_TOKEN = "abc123";

	/**
	 * Ensure that instances of {@link RateSound} are correctly constructed.
	 */
	@Test
	public void rateSoundCorrectlyCreated() {
		final RateSound rateSound = newQueryInstance();

		assertEquals(OAUTH_TOKEN, rateSound.getOauthToken());
		assertEquals(
				String.valueOf(SOUND_ID),
				rateSound.getRouteParameters().get(RateSound.SOUND_IDENTIFIER_ROUTE_PARAMETER));
		assertEquals(String.valueOf(RATING), rateSound.getQueryParameters().get(RateSound.RATING_PARAMETER_NAME));
	}

	/**
	 * Ensure that an {@link IllegalArgumentException} is thrown if we try to apply a rating higher than the maximum.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ratingGreaterThanFive() {
		new RateSound(SOUND_ID, 6, OAUTH_TOKEN);
	}

	/**
	 * Ensure that an {@link IllegalArgumentException} is thrown if we try to apply a rating lower than the minimum.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void ratingLessThanZero() {
		new RateSound(SOUND_ID, -1, OAUTH_TOKEN);
	}

	@Override
	protected RateSound newQueryInstance() {
		return new RateSound(SOUND_ID, RATING, OAUTH_TOKEN);
	}

}
