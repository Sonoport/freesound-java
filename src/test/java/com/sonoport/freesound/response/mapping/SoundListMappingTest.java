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

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.PagingResponse;
import com.sonoport.freesound.response.Sound;

/**
 * Unit tests to ensure the correct operation of {@link PagingResponseMapper} when handling sounds and {@link Sound}
 * objects.
 *
 * The test data used is defined in <code>/src/test/resources/sound-list.json</code>.
 */
public class SoundListMappingTest extends MapperTest {

	/** The value for the number of results. */
	private static final Integer COUNT = Integer.valueOf(4557);

	/** URI to the next page of results. */
	private static final String NEXT_PAGE =
			"http://www.freesound.org/apiv2/search/text/?&query=cars&page=3&page_size=3";

	/** URI to the previous page of results. */
	private static final String PREVIOUS_PAGE =
			"http://www.freesound.org/apiv2/search/text/?&query=cars&page=1&page_size=3";

	/** Instance of {@link SoundResultsListMapper} to use in tests. */
	private final PagingResponseMapper<Sound> mapper = new PagingResponseMapper<>(new SoundMapper());

	/**
	 * Ensure that mapper correctly parses the JSON structure of the search results.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseSoundResults() throws Exception {
		final JSONObject jsonSearchResults = readJSONFile("/sound-list.json");

		final PagingResponse<Sound> soundResultsList = mapper.map(jsonSearchResults);

		assertEquals(COUNT, Integer.valueOf(soundResultsList.getCount()));
		assertEquals(NEXT_PAGE, soundResultsList.getNextPageURI());
		assertEquals(PREVIOUS_PAGE, soundResultsList.getPreviousPageURI());

		assertTrue(soundResultsList.getItems().size() == 3);
	}
}
