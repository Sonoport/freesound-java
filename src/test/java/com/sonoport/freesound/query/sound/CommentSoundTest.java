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
 * Unit tests to ensure the correct operation of {@link CommentSound}.
 */
public class CommentSoundTest extends JSONResponseQueryTest<CommentSound> {

	/** Sound identifier to use in tests. */
	private static final int SOUND_ID = 1234;

	/** Comment to use in tests. */
	private static final String COMMENT = "Great Sound!";

	/** OAuth token to use in tests. */
	private static final String OAUTH_TOKEN = "123abc456";

	/**
	 * Ensure that instances of {@link CommentSound} are correctly constructed.
	 */
	@Test
	public void commentSoundCorrectlyCreated() {
		final CommentSound commentSound = newQueryInstance();

		assertEquals(OAUTH_TOKEN, commentSound.getOauthToken());
		assertEquals(
				String.valueOf(SOUND_ID),
				commentSound.getRouteParameters().get(CommentSound.SOUND_IDENTIFIER_ROUTE_PARAMETER));
		assertEquals(COMMENT, commentSound.getQueryParameters().get(CommentSound.COMMENT_PARAMETER_NAME));
	}

	@Override
	protected CommentSound newQueryInstance() {
		return new CommentSound(SOUND_ID, COMMENT, OAUTH_TOKEN);
	}

}
