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

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.License;
import com.sonoport.freesound.response.Sound;

/**
 * Unit tests to ensure the correct operation of {@link SoundMapper}. Source data is stored at
 * <code>/src/test/resources/sound.json</code>.
 */
public class SoundMapperTest extends MapperTest {

	/** Sound identifier from file. */
	private static final Integer ID = Integer.valueOf(42937);

	/** URL field from file. */
	private static final String URL = "http://www.freesound.org/people/AGFX/sounds/42937/";

	/** Sound name from file. */
	private static final String NAME = "Elec hand drill drilling metal rough 2.wav";

	/** Collection of tags from file. */
	private static final Set<String> TAGS =
			new HashSet<String>(
					Arrays.asList(
							"environmental-sounds-research",
							"electric",
							"drilling",
							"recording",
							"tool",
							"workshop",
							"field"));

	/** Description from file. */
	private static final String DESCRIPTION = "Electric hand drill with bit drilling and grinding into metal plate";

	/** Geotag value from file. */
	private static final String GEOTAG = null;

	/** Creation date from file. The number here is the equivalent time-since-epoch value for 2007-10-25T18:43:27. */
	private static final Date DATE_CREATED = new Date(1193337807000L);

	/** License value from file. */
	private static final License LICENSE = License.CC_ATTRIBUTION;

    /** Sound file type from file. */
    private static final String TYPE = "wav";

	/** Number of channels from file. */
	private static final Integer CHANNELS = Integer.valueOf(1);

	/** Sound file size from file. */
	private static final Integer FILE_SIZE = Integer.valueOf(1259334);

	/** Bitrate from file. */
	private static final Integer BITRATE = Integer.valueOf(1152);

	/** Bit depth from file. */
	private static final Integer BITDEPTH = Integer.valueOf(24);

    /** Duration from file. */
    private static final Float DURATION = Float.valueOf("8.71440277778");

    /** Sapmle rate from file. */
    private static final Float SAMPLE_RATE = Float.valueOf("48000.0");

    /** Username from file. */
    private static final String USERNAME = "AGFX";

    /** Pack URI from file. */
    private static final String PACK = "http://www.freesound.org/apiv2/packs/2716/";

    /** Download URI from file. */
    private static final String DOWNLOAD_URI = "https://www.freesound.org/apiv2/sounds/42937/download/";

    /** Bookmark URI from file. */
    private static final String BOOKMARK_URI = "https://www.freesound.org/apiv2/sounds/42937/bookmark/";

    /** Details of previews from file. */
    private static final Map<String, String> PREVIEWS;
    static {
    	PREVIEWS = new HashMap<String, String>();
    	PREVIEWS.put("preview-lq-ogg", "http://www.freesound.org/data/previews/42/42937_50975-lq.ogg");
    	PREVIEWS.put("preview-lq-mp3", "http://www.freesound.org/data/previews/42/42937_50975-lq.mp3");
		PREVIEWS.put("preview-hq-ogg", "http://www.freesound.org/data/previews/42/42937_50975-hq.ogg");
		PREVIEWS.put("preview-hq-mp3", "http://www.freesound.org/data/previews/42/42937_50975-hq.mp3");
    }

    /** Details of images from file. */
    private static final Map<String, String> IMAGES;
    static {
    	IMAGES = new HashMap<String, String>();
    	IMAGES.put("waveform_l", "http://www.freesound.org/data/displays/42/42937_50975_wave_L.png");
    	IMAGES.put("waveform_m", "http://www.freesound.org/data/displays/42/42937_50975_wave_M.png");
    	IMAGES.put("spectral_m", "http://www.freesound.org/data/displays/42/42937_50975_spec_M.jpg");
    	IMAGES.put("spectral_l", "http://www.freesound.org/data/displays/42/42937_50975_spec_L.jpg");
    }

    /** Number of downloads from file. */
    private static final Integer NUMBER_OF_DOWNLOADS = Integer.valueOf(4434);

    /** Average rating from file. */
    private static final Float AVERAGE_RATING = Float.valueOf("4.0");

    /** Number of ratings from file. */
    private static final Integer NUMBER_OF_RATINGS = Integer.valueOf(52);

    /** Rating URI from file. */
    private static final String RATING_URI = "https://www.freesound.org/apiv2/sounds/42937/rate/";

    /** Comments URI from file. */
    private static final String COMMENTS_URI = "http://www.freesound.org/apiv2/sounds/42937/comments/";

    /** Number of comments from file. */
    private static final Integer NUMBER_OF_COMMENTS = Integer.valueOf(6);

    /** Comment URI from file. */
    private static final String COMMENT_URI = "https://www.freesound.org/apiv2/sounds/42937/comment/";

    /** Similar sounds URI from file. */
    private static final String SIMILAR_SOUNDS_URI = "http://www.freesound.org/apiv2/sounds/42937/similar/";

	/** Instance of {@link SoundMapper} to use in tests. */
	private final SoundMapper mapper = new SoundMapper();

	/**
	 * Ensure that {@link SoundMapper} correctly maps the JSON representation of a sound into a {@link Sound} object.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseSoundRecord() throws Exception {
		final JSONObject jsonSound = readJSONFile("/sound.json");

		final Sound sound = mapper.map(jsonSound);

		 assertEquals(ID, sound.getId());
		 assertEquals(URL, sound.getUrl());
		 assertEquals(NAME, sound.getName());
		 assertEquals(TAGS, sound.getTags());
		 assertEquals(DESCRIPTION, sound.getDescription());
		 assertEquals(GEOTAG, sound.getGeotag());
		 assertEquals(DATE_CREATED, sound.getCreated());
		 assertEquals(LICENSE, sound.getLicense());
		 assertEquals(TYPE, sound.getType());
		 assertEquals(CHANNELS, sound.getChannels());
		 assertEquals(FILE_SIZE, sound.getFilesize());
		 assertEquals(BITRATE, sound.getBitrate());
		 assertEquals(BITDEPTH, sound.getBitdepth());
		 assertEquals(DURATION, sound.getDuration());
		 assertEquals(SAMPLE_RATE, sound.getSamplerate());
		 assertEquals(USERNAME, sound.getUsername());
		 assertEquals(PACK, sound.getPack());
		 assertEquals(DOWNLOAD_URI, sound.getDownloadURI());
		 assertEquals(BOOKMARK_URI, sound.getBookmarkURI());
		 compareMaps(PREVIEWS, sound.getPreviews());
		 compareMaps(IMAGES, sound.getImages());
		 assertEquals(NUMBER_OF_DOWNLOADS, sound.getNumberOfDownloads());
		 assertEquals(AVERAGE_RATING, sound.getAverageRating());
		 assertEquals(NUMBER_OF_RATINGS, sound.getNumberOfRatings());
		 assertEquals(RATING_URI, sound.getRatingURI());
		 assertEquals(COMMENTS_URI, sound.getCommentsURI());
		 assertEquals(NUMBER_OF_COMMENTS, sound.getNumberOfComments());
		 assertEquals(COMMENT_URI, sound.getCommentURI());
		 assertEquals(SIMILAR_SOUNDS_URI, sound.getSimilarSoundsURI());
	}

}
