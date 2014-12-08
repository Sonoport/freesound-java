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

import com.sonoport.freesound.License;
import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link UploadSound}.
 */
public class UploadSoundTest extends JSONResponseQueryTest<UploadSound> {

	/** Description to use in tests. */
	private static final String DESCRIPTION = "Sound Description";

	/** License to use in tests. */
	private static final License LICENSE = License.CC_0;

	/** Tag to use in tests. */
	private static final String TAG_1 = "tag";

	/** Tag to use in tests. Contains a space that should be replaced with a hypen before being passed in to the API. */
	private static final String TAG_2 = "second tag";

	/** Tag to use in tests. */
	private static final String TAG_3 = "another-tag";

	/** Collection of tags to use in tests. */
	private static final Set<String> TAGS = new HashSet<>(Arrays.asList(TAG_1, TAG_2));

	/** Format of {@link #TAG_2} when it is passed to the API. */
	private static final String EXPECTED_TAG_2 = "second-tag";

	/** Sound name to use in tests. */
	private static final String NAME = "Sound Name";

	/** Pack name to use in tests. */
	private static final String PACK = "test";

	/** Latitude to use in tests. */
	private static final double LATITUDE = 2.145677;

	/** Longitude to use in tests. */
	private static final double LONGITUDE = -3.22345;

	/** Zoom to use in tests. */
	private static final int ZOOM = 14;

	/** {@link Geotag} object to use in tests. */
	private static final Geotag GEOTAG = new Geotag(LATITUDE, LONGITUDE, ZOOM);

	/** String the {@link Geotag} object should be converted into. */
	private static final String EXPECTED_GEOTAG_STRING = LATITUDE + "," + LONGITUDE + "," + ZOOM;

	/** OAuth2 access token to use in tests. */
	private static final String OAUTH_TOKEN = "abc123def";

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

	/**
	 * Ensure Fluent API methods correctly populate the appropriate query parameters.
	 */
	@Test
	public void fluentAPICorrectlyPopulatesParameters() {
		final UploadSound uploadSound =
				newQueryInstance()
					.description(DESCRIPTION)
					.license(LICENSE)
					.tags(TAGS)
					.tag(TAG_3)
					.name(NAME)
					.pack(PACK)
					.geotag(GEOTAG);

		assertTrue(uploadSound.getRouteParameters().isEmpty());
		assertSame(mockFile, uploadSound.getQueryParameters().get(UploadSound.SOUND_FILE_PARAMETER_NAME));
		assertEquals(DESCRIPTION, uploadSound.getQueryParameters().get(UploadSound.DESCRIPTION_PARAMETER_NAME));

		// Upload sound uses the textual description of the license
		assertEquals(
				LICENSE.getDescription(), uploadSound.getQueryParameters().get(UploadSound.LICENSE_PARAMETER_NAME));
		assertEquals(OAUTH_TOKEN, uploadSound.getOauthToken());

		final String tagsString = (String) uploadSound.getQueryParameters().get(UploadSound.TAGS_PARAMETER_NAME);
		final Set<String> tags = new HashSet<>(Arrays.asList(tagsString.split(" ")));
		assertTrue(tags.size() == 3);
		assertTrue(tags.contains(TAG_1));
		assertTrue(tags.contains(EXPECTED_TAG_2));
		assertTrue(tags.contains(TAG_3));

		assertEquals(NAME, uploadSound.getQueryParameters().get(UploadSound.SOUND_NAME_PARAMETER_NAME));
		assertEquals(PACK, uploadSound.getQueryParameters().get(UploadSound.PACK_PARAMETER_NAME));
		assertEquals(EXPECTED_GEOTAG_STRING, uploadSound.getQueryParameters().get(UploadSound.GEOTAG_PARAMETER_NAME));
	}

	@Override
	protected UploadSound newQueryInstance() {
		return new UploadSound(mockFile, OAUTH_TOKEN);
	}

}
