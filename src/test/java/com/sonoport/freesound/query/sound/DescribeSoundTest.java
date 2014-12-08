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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of {@link DescribeSound}.
 */
public class DescribeSoundTest extends SoundUploadQueryTest<DescribeSound> {

	/** Name of previously uploaded file. */
	private static final String UPLOAD_FILENAME = "sound.wav";

	/**
	 * Ensure that instances of {@link DescribeSound} are constructed correctly.
	 */
	@Test
	public void describeSoundQueryCreatedCorrectly() {
		final DescribeSound describeSoundQuery = newQueryInstance();

		assertTrue(describeSoundQuery.getRouteParameters().isEmpty());
		assertSame(
				UPLOAD_FILENAME,
				describeSoundQuery.getQueryParameters().get(DescribeSound.UPLOAD_FILENAME_PARAMETER_NAME));
		assertEquals(DESCRIPTION, describeSoundQuery.getQueryParameters().get(UploadSound.DESCRIPTION_PARAMETER_NAME));

		// Upload sound uses the textual description of the license
		assertEquals(
				LICENSE.getDescription(),
				describeSoundQuery.getQueryParameters().get(UploadSound.LICENSE_PARAMETER_NAME));
		assertEquals(OAUTH_TOKEN, describeSoundQuery.getOauthToken());

		final String tagsString = (String) describeSoundQuery.getQueryParameters().get(UploadSound.TAGS_PARAMETER_NAME);
		final Set<String> tags = new HashSet<>(Arrays.asList(tagsString.split(" ")));
		assertTrue(tags.size() == 2);
		assertTrue(tags.contains(TAG_1));
		assertTrue(tags.contains(EXPECTED_TAG_2));
	}

	@Override
	protected DescribeSound newQueryInstance() {
		return new DescribeSound(UPLOAD_FILENAME, DESCRIPTION, LICENSE, TAGS, OAUTH_TOKEN);
	}

}
