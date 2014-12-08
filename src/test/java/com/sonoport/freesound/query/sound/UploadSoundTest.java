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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import mockit.Mocked;

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of {@link UploadSound}.
 */
public class UploadSoundTest extends SoundUploadQueryTest<UploadSound> {

	/** Mock {@link File} object to use in tests. */
	@Mocked
	private File mockFile;

	/**
	 * Ensure that {@link UploadSound} objects are correctly created when using the simple (one argument) constructor.
	 */
	@Test
	public void simpleConstructor() {
		final UploadSound uploadSound = newQueryInstance();

		assertTrue(uploadSound.getRouteParameters().isEmpty());
		assertSame(mockFile, uploadSound.getQueryParameters().get(UploadSound.SOUND_FILE_PARAMETER_NAME));
		assertEquals(OAUTH_TOKEN, uploadSound.getOauthToken());
	}

	/**
	 * Ensure that {@link UploadSound} objects are correctly created when using the complex (four argument) constructor.
	 */
	@Test
	public void completeConstructor() {
		final UploadSound uploadSound = new UploadSound(mockFile, DESCRIPTION, LICENSE, TAGS, OAUTH_TOKEN);

		assertTrue(uploadSound.getRouteParameters().isEmpty());
		assertSame(mockFile, uploadSound.getQueryParameters().get(UploadSound.SOUND_FILE_PARAMETER_NAME));
		assertEquals(DESCRIPTION, uploadSound.getQueryParameters().get(UploadSound.DESCRIPTION_PARAMETER_NAME));

		// Upload sound uses the textual description of the license
		assertEquals(
				LICENSE.getDescription(), uploadSound.getQueryParameters().get(UploadSound.LICENSE_PARAMETER_NAME));
		assertEquals(OAUTH_TOKEN, uploadSound.getOauthToken());

		final String tagsString = (String) uploadSound.getQueryParameters().get(UploadSound.TAGS_PARAMETER_NAME);
		final Set<String> tags = new HashSet<>(Arrays.asList(tagsString.split(" ")));
		assertTrue(tags.size() == 2);
		assertTrue(tags.contains(TAG_1));
		assertTrue(tags.contains(EXPECTED_TAG_2));
	}

	@Override
	protected UploadSound newQueryInstance() {
		return new UploadSound(mockFile, OAUTH_TOKEN);
	}

}
