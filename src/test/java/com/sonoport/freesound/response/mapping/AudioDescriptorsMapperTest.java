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

import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.AudioDescriptors;

/**
 * Unit tests to ensure the correct operation of {@link AudioDescriptorsMapper}.
 *
 * Test data is located at <code>/src/test/resources/audio-descriptors.json</code>.
 */
public class AudioDescriptorsMapperTest extends MapperTest {

	/** Descriptor from test file. */
	private static final String ONE_DIMENSIONAL_DESCRIPTOR_1 = "lowlevel.average_loudness";

	/** Descriptor from test file. */
	private static final String ONE_DIMENSIONAL_DESCRIPTOR_2 = "lowlevel.barkbands.dmean";

	/** Descriptor from test file. */
	private static final String MULTI_DIMENSIONAL_DESCRIPTOR_1 = "lowlevel.barkbands.dmean";

	/** Descriptor from test file. */
	private static final String MULTI_DIMENSIONAL_DESCRIPTOR_2 = "lowlevel.barkbands.dmean2";

	/** Descriptor from test file. */
	private static final String VARIABLE_LENGTH_DESCRIPTOR = "rhythm.beats_position";

	/** Instance of {@link AudioDescriptorsMapper} to use in tests. */
	private final AudioDescriptorsMapper mapper = new AudioDescriptorsMapper();

	/**
	 * Ensure that correctly formatted JSON message is correctly converted into a {@link AudioDescriptors} object.
	 *
	 * @throws Exception Any exceptions trown in test
	 */
	@Test
	public void audioDescriptorsMapping() throws Exception {
		final JSONObject audioDescriptorsJSON = readJSONFile("/audio-descriptors.json");

		final AudioDescriptors audioDescriptors = mapper.map(audioDescriptorsJSON);

		assertTrue(audioDescriptors.getFixedLengthOneDimensional().size() == 2);
		assertTrue(audioDescriptors.getFixedLengthOneDimensional().contains(ONE_DIMENSIONAL_DESCRIPTOR_1));
		assertTrue(audioDescriptors.getFixedLengthOneDimensional().contains(ONE_DIMENSIONAL_DESCRIPTOR_2));

		assertTrue(audioDescriptors.getFixedLengthMultiDimensional().size() == 2);
		assertTrue(audioDescriptors.getFixedLengthMultiDimensional().contains(MULTI_DIMENSIONAL_DESCRIPTOR_1));
		assertTrue(audioDescriptors.getFixedLengthMultiDimensional().contains(MULTI_DIMENSIONAL_DESCRIPTOR_2));

		assertTrue(audioDescriptors.getVariableLength().size() == 1);
		assertTrue(audioDescriptors.getVariableLength().contains(VARIABLE_LENGTH_DESCRIPTOR));
	}
}
