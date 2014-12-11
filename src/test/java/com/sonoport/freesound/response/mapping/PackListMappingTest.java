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

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.Pack;
import com.sonoport.freesound.response.PagingResponse;

/**
 * Unit tests to ensure that lists of {@link Pack}s returned by the API (for example, from the 'User Packs' query) are
 * correctly parsed and mapped.
 *
 * Source data file can be found at <code>/src/test/resources/pack-list.json</code>.
 */
public class PackListMappingTest extends MapperTest {

	/** Total number of results. */
	private static final Integer COUNT = Integer.valueOf(33);

	/** URL to next page of results. */
	private static final String NEXT_PAGE_URI = "http://www.freesound.org/apiv2/users/reinsamba/packs/?page=3";

	/** URL to previous page of results. */
	private static final String PREVIOUS_PAGE_URI = "http://www.freesound.org/apiv2/users/reinsamba/packs/?page=1";

	/** Pack identifier. */
	private static final int PACK_ID = 2174;

	/** URL of pack. */
	private static final String PACK_URL = "http://www.freesound.org/people/reinsamba/packs/2174/";

	/** Pack description. */
	private static final String PACK_DESCRIPTION = "Pack description";

	/** Date pack was created. Number is milliseconds since epoch for 2007-05-08T22:26:23. */
	private static final Date PACK_CREATED_DATE = new Date(1178663183000L);

	/** Name of pack. */
	private static final String PACK_NAME = "whistle";

	/** Username of pack owner. */
	private static final String PACK_USERNAME = "reinsamba";

	/** Number of sounds in pack. */
	private static final int PACK_SOUNDS = 5;

	/** URL to pack sounds. */
	private static final String PACK_SOUNDS_URL = "http://www.freesound.org/apiv2/packs/2174/sounds/";

	/** Number of times pack has been downloaded. */
	private static final int PACK_DOWNLOADS = 403;

	/** Mapper used to process the JSON representation into DTOs. */
	private final PagingResponseMapper<Pack> mapper = new PagingResponseMapper<>(new PackMapper());

	/**
	 * Test that a properly formatted response is correctly processed into a {@link PagingResponse} object.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parsePackList() throws Exception {
		final JSONObject packListJSON = readJSONFile("/pack-list.json");

		final List<Pack> packList = mapper.map(packListJSON);

		assertTrue(packList.size() == 1);

		final Pack pack = packList.get(0);
		assertEquals(PACK_ID, pack.getId());
		assertEquals(PACK_URL, pack.getUrl());
		assertEquals(PACK_DESCRIPTION, pack.getDescription());
		assertEquals(PACK_CREATED_DATE, pack.getCreated());
		assertEquals(PACK_NAME, pack.getName());
		assertEquals(PACK_USERNAME, pack.getUsername());
		assertEquals(PACK_SOUNDS, pack.getNumberOfSounds());
		assertEquals(PACK_SOUNDS_URL, pack.getSoundsURI());
		assertEquals(PACK_DOWNLOADS, pack.getNumberOfDownloads());
	}
}
