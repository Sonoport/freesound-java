package com.sonoport.freesound.response.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.Pack;

/**
 * Unit tests to ensure the correct operation of {@link PackMapper}. Test data is located as
 * <code>/src/test/resources/pack.json</code>.
 */
public class PackMapperTest extends MapperTest {

	/** Id of the pack. */
	private static final int ID = 15292;

	/** URL of the pack. */
	private static final String URL = "http://www.freesound.org/people/clarinet_pablo_proj/packs/15292/";

	/** Description of the pack. */
	private static final String DESCRIPTION = "Pack description";

	/** Date the pack was created. */
	private static final Date DATE_CREATED = new Date(1410973300000L);

	/** Name of the pack. */
	private static final String PACK_NAME = "Pablo_Project clarinet overall quality of single note";

	/** User name of the pack creator. */
	private static final String USERNAME = "clarinet_pablo_proj";

	/** Number of sounds in the pack. */
	private static final int NUMBER_OF_SOUNDS = 794;

	/** URL link to sounds in pack. */
	private static final String SOUNDS_URI = "http://www.freesound.org/apiv2/packs/15292/sounds/";

	/** Number of times the pack has been downloaded. */
	private static final int NUMBER_OF_DOWNLOADS = 12;

	/** Instance of {@link PackMapper} to use in tests. */
	private final PackMapper packMapper = new PackMapper();

	/**
	 * Test that {@link PackMapper} correctly maps the JSON representation of a sound pack into a {@link Pack} DTO.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parsePackRecord() throws Exception {
		final JSONObject packJSON = readJSONFile("/pack.json");

		final Pack pack = packMapper.map(packJSON);

		assertEquals(ID, pack.getId());
		assertEquals(URL, pack.getUrl());
		assertEquals(DESCRIPTION, pack.getDescription());
		assertEquals(DATE_CREATED, pack.getCreated()); // 2014-09-17T17:01:40.882
		assertEquals(PACK_NAME, pack.getName());
		assertEquals(USERNAME, pack.getUsername());
		assertEquals(NUMBER_OF_SOUNDS, pack.getNumberOfSounds());
		assertEquals(SOUNDS_URI, pack.getSoundsURI());
		assertEquals(NUMBER_OF_DOWNLOADS, pack.getNumberOfDownloads());
	}
}
