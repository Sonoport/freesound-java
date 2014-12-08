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
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.License;
import com.sonoport.freesound.response.PendingUploads;
import com.sonoport.freesound.response.Sound;

/**
 *Unit tests to ensure the correct operation of {@link PendingUploadsMapper}. Source data for tests can be found at
 *<code>/src/test/resources/pending-uploads.json</code>.
 */
public class PendingUploadsMapperTest extends MapperTest {

	/** Name of sound awaiting description. */
	private static final String PENDING_DESCRIPTION_SOUND_NAME = "goat_bleat_01.mp3";

	/** Id of sound awaiting moderation. */
	private static final Integer PENDING_MODERATION_SOUND_ID = Integer.valueOf(256312);

	/** Name of sound awaiting moderation. */
	private static final String PENDING_MODERATION_SOUND_NAME = "goat_bleat_02.mp3";

	/** Description of sound awaiting moderation. */
	private static final String PENDING_MODERATION_SOUND_DESCRIPTION = "Goat bleat 2";

	/** License of sound awaiting moderation. */
	private static final License PENDING_MODERATION_SOUND_LICENSE = License.CC_ATTRIBUTION_NONCOMMERCIAL;

	/** Tags associated with sound awaiting moderation. */
	private static final Set<String> PENDING_MODERATION_SOUND_TAGS =
			new HashSet<>(Arrays.asList("animal", "goat", "bleat"));

	/** Date sound awaiting moderation was created. */
	private static final Date PENDING_MODERATION_SOUND_CREATED_DATE = new Date(1417458563000L);

	/** Images associated with sound awaiting moderation. */
	private static final Map<String, String> PENDING_MODERATION_SOUND_IMAGES;
	static {
		PENDING_MODERATION_SOUND_IMAGES = new HashMap<String, String>();
		PENDING_MODERATION_SOUND_IMAGES.put(
				"waveform_l", "https://www.freesound.org/data/displays/256/256312_4709044_wave_L.png");
		PENDING_MODERATION_SOUND_IMAGES.put(
				"waveform_m", "https://www.freesound.org/data/displays/256/256312_4709044_wave_M.png");
		PENDING_MODERATION_SOUND_IMAGES.put(
				"spectral_m", "https://www.freesound.org/data/displays/256/256312_4709044_spec_M.jpg");
		PENDING_MODERATION_SOUND_IMAGES.put(
				"spectral_l", "https://www.freesound.org/data/displays/256/256312_4709044_spec_L.jpg");
	}

	/** Instance of {@link PendingUploadsMapper} to use in tests. */
	private final PendingUploadsMapper mapper = new PendingUploadsMapper();

	/**
	 * Test that a properly formatted JSON response is correctly processed by the mapper.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void mapPendingUploads() throws Exception {
		final JSONObject pendingUploadsJSON = readJSONFile("/pending-uploads.json");

		final PendingUploads pendingUploads = mapper.map(pendingUploadsJSON);

		assertTrue(pendingUploads.getPendingDescription().size() == 1);
		assertTrue(pendingUploads.getPendingDescription().contains(PENDING_DESCRIPTION_SOUND_NAME));

		assertTrue(pendingUploads.getPendingModeration().size() == 1);
		final Sound soundPendingModeration = pendingUploads.getPendingModeration().get(0);
		assertEquals(PENDING_MODERATION_SOUND_ID, soundPendingModeration.getId());
		assertEquals(PENDING_MODERATION_SOUND_NAME, soundPendingModeration.getName());
		assertEquals(PENDING_MODERATION_SOUND_DESCRIPTION, soundPendingModeration.getDescription());
		assertEquals(PENDING_MODERATION_SOUND_LICENSE, soundPendingModeration.getLicense());
		assertEquals(PENDING_MODERATION_SOUND_CREATED_DATE, soundPendingModeration.getCreated());
		assertTrue(PENDING_MODERATION_SOUND_TAGS.size() == soundPendingModeration.getTags().size());
		assertTrue(soundPendingModeration.getTags().containsAll(PENDING_MODERATION_SOUND_TAGS));
		assertTrue(PENDING_MODERATION_SOUND_IMAGES.size() == soundPendingModeration.getImages().size());

		final Map<String, String> images = soundPendingModeration.getImages();
		for (final Entry<String, String> expectedImage : PENDING_MODERATION_SOUND_IMAGES.entrySet()) {
			assertEquals(expectedImage.getValue(), images.get(expectedImage.getKey()));
		}

		assertTrue(pendingUploads.getPendingProcessing().size() == 0);
	}
}
