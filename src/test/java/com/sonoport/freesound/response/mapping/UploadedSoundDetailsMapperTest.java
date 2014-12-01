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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.UploadedSoundDetails;

/**
 * Unit tests to ensure the correct operation of {@link UploadedSoundDetailsMapper}.
 *
 * Test data can be found at:
 * <code>/src/test/resources/uploaded-described-sound.json</code>
 * <code>/src/test/resources/uploaded-undescribed-sound.json</code>
 */
public class UploadedSoundDetailsMapperTest extends MapperTest {

	/** Detail message in 'described sound' response. */
	private static final String DESCRIBED_SOUND_DETAIL =
			"Audio file successfully uploaded and described (now pending processing and moderation)";

	/** Sound id in 'described sound' response. */
	private static final Integer DESCRIBED_SOUND_ID = 12345;

	/** Detail message in 'undescribed sound' response. */
	private static final String UNDESCRIBED_SOUND_DETAIL =
			"Audio file successfully uploaded (2000 bytes, now pending description)";

	/** Sound filename in 'undescribed sound' response. */
	private static final String UNDESCRIBED_SOUND_FILENAME = "foo.wav";

	/** Instance of {@link UploadedSoundDetailsMapper} to use in tests. */
	private final UploadedSoundDetailsMapper mapper = new UploadedSoundDetailsMapper();

	/**
	 * Transform the response to uploading a sound with a description.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void transformDescribedUploadedSoundResponse() throws Exception {
		final JSONObject uploadedSoundJSON = readJSONFile("/uploaded-described-sound.json");

		final UploadedSoundDetails uploadedSoundDetails = mapper.map(uploadedSoundJSON);
		assertEquals(DESCRIBED_SOUND_DETAIL, uploadedSoundDetails.getDetail());
		assertEquals(DESCRIBED_SOUND_ID, uploadedSoundDetails.getId());
		assertNull(uploadedSoundDetails.getFilename());
	}

	/**
	 * Transform the response to uploading a sound without a description.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void transformUndescribedUploadedSoundResponse() throws Exception {
		final JSONObject uploadedSoundJSON = readJSONFile("/uploaded-undescribed-sound.json");

		final UploadedSoundDetails uploadedSoundDetails = mapper.map(uploadedSoundJSON);
		assertEquals(UNDESCRIBED_SOUND_DETAIL, uploadedSoundDetails.getDetail());
		assertEquals(UNDESCRIBED_SOUND_FILENAME, uploadedSoundDetails.getFilename());
		assertNull(uploadedSoundDetails.getId());
	}
}
