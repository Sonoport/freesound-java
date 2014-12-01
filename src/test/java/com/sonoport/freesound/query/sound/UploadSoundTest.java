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

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link UploadSound}.
 */
public class UploadSoundTest extends JSONResponseQueryTest<UploadSound> {

	/** Description to use in tests. */
	private static final String DESCRIPTION = "Sound Description";

	/** License to use in tests. */
	private static final String LICENSE = "Creative Commons 0";

	/** Tag to use in tests. */
	private static final String TAG_1 = "tag";

	/** Tag to use in tests. Contains a space that should be replaced with a hypen before being passed in to the API. */
	private static final String TAG_2 = "second tag";

	/** Collection of tags to use in tests. */
	private static final Set<String> TAGS = new HashSet<>(Arrays.asList(TAG_1, TAG_2));

	/** Format of {@link #TAG_2} when it is passed to the API. */
	private static final String EXPECTED_TAG_2 = "second-tag";

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
	}

	/**
	 * Ensure that {@link UploadSound} objects are correctly created when using the complex (four argument) constructor.
	 */
	@Test
	public void completeConstructor() {
		final UploadSound uploadSound = new UploadSound(mockFile, DESCRIPTION, LICENSE, TAGS);

		assertTrue(uploadSound.getRouteParameters().isEmpty());
		assertSame(mockFile, uploadSound.getQueryParameters().get(UploadSound.SOUND_FILE_PARAMETER_NAME));
		assertEquals(DESCRIPTION, uploadSound.getQueryParameters().get(UploadSound.DESCRIPTION_PARAMETER_NAME));
		assertEquals(LICENSE, uploadSound.getQueryParameters().get(UploadSound.LICENSE_PARAMETER_NAME));

		final String tagsString = (String) uploadSound.getQueryParameters().get(UploadSound.TAGS_PARAMETER_NAME);
		final Set<String> tags = new HashSet<>(Arrays.asList(tagsString.split(" ")));
		assertTrue(tags.size() == 2);
		assertTrue(tags.contains(TAG_1));
		assertTrue(tags.contains(EXPECTED_TAG_2));
	}

	@Override
	protected UploadSound newQueryInstance() {
		return new UploadSound(mockFile);
	}

}
