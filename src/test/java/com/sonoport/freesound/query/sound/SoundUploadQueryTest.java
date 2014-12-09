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
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.sonoport.freesound.License;
import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests for the common code provided in {@link AbstractSoundUploadQuery}.
 *
 * @param <T> Type of the subclass being tested
 */
public abstract class SoundUploadQueryTest<T extends AbstractSoundUploadQuery<?, T>> extends JSONResponseQueryTest<T> {

	/** Description to use in tests. */
	protected static final String DESCRIPTION = "Sound Description";

	/** License to use in tests. */
	protected static final License LICENSE = License.CC_0;

	/** Tag to use in tests. */
	protected static final String TAG_1 = "tag";

	/** Tag to use in tests. Contains a space that should be replaced with a hypen before being passed in to the API. */
	protected static final String TAG_2 = "second tag";

	/** Tag to use in tests. */
	protected static final String TAG_3 = "another-tag";

	/** Collection of tags to use in tests. */
	protected static final Set<String> TAGS = new HashSet<>(Arrays.asList(TAG_1, TAG_2));

	/** Format of {@link #TAG_2} when it is passed to the API. */
	protected static final String EXPECTED_TAG_2 = "second-tag";

	/** Sound name to use in tests. */
	protected static final String NAME = "Sound Name";

	/** Pack name to use in tests. */
	protected static final String PACK = "test";

	/** Latitude to use in tests. */
	protected static final double LATITUDE = 2.145677;

	/** Longitude to use in tests. */
	protected static final double LONGITUDE = -3.22345;

	/** Zoom to use in tests. */
	protected static final int ZOOM = 14;

	/** {@link Geotag} object to use in tests. */
	protected static final Geotag GEOTAG = new Geotag(LATITUDE, LONGITUDE, ZOOM);

	/** String the {@link Geotag} object should be converted into. */
	private static final String EXPECTED_GEOTAG_STRING = LATITUDE + "," + LONGITUDE + "," + ZOOM;

	/** OAuth2 access token to use in tests. */
	protected static final String OAUTH_TOKEN = "abc123def";

	/**
	 * Ensure Fluent API methods correctly populate the appropriate query parameters.
	 */
	@Test
	public void fluentAPICorrectlyPopulatesParameters() {
		final T soundUploadQuery =
				newQueryInstance()
					.description(DESCRIPTION)
					.license(LICENSE)
					.tags(TAGS)
					.tag(TAG_3)
					.name(NAME)
					.pack(PACK)
					.geotag(GEOTAG);

		assertEquals(OAUTH_TOKEN, soundUploadQuery.getOauthToken());
		assertEquals(DESCRIPTION, soundUploadQuery.getQueryParameters().get(UploadSound.DESCRIPTION_PARAMETER_NAME));

		// Upload sound uses the textual description of the license
		assertEquals(
				LICENSE.getDescription(),
				soundUploadQuery.getQueryParameters().get(UploadSound.LICENSE_PARAMETER_NAME));
		assertEquals(OAUTH_TOKEN, soundUploadQuery.getOauthToken());

		final String tagsString = (String) soundUploadQuery.getQueryParameters().get(UploadSound.TAGS_PARAMETER_NAME);
		final Set<String> tags = new HashSet<>(Arrays.asList(tagsString.split(" ")));
		assertTrue(tags.size() == 3);
		assertTrue(tags.contains(TAG_1));
		assertTrue(tags.contains(EXPECTED_TAG_2));
		assertTrue(tags.contains(TAG_3));

		assertEquals(NAME, soundUploadQuery.getQueryParameters().get(UploadSound.SOUND_NAME_PARAMETER_NAME));
		assertEquals(PACK, soundUploadQuery.getQueryParameters().get(UploadSound.PACK_PARAMETER_NAME));
		assertEquals(
				EXPECTED_GEOTAG_STRING, soundUploadQuery.getQueryParameters().get(UploadSound.GEOTAG_PARAMETER_NAME));
	}
}
