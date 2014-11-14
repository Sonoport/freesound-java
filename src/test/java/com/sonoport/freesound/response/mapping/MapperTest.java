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

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

/**
 * Abstract class containing common methods used when testing mapping objects.
 */
public abstract class MapperTest {

	/**
	 * Read a given JSON file into a {@link JSONObject}. Files should be stored under <code>/src/test/resources</code>.
	 *
	 * @param resourcePath The path to the file
	 * @return {@link JSONObject} representation of the file contents
	 *
	 * @throws Exception Any exceptions thrown when reading the file
	 */
	protected JSONObject readJSONFile(final String resourcePath) throws Exception {
		final URI soundFileURI = getClass().getResource(resourcePath).toURI();
		final Path filePath = Paths.get(soundFileURI);
		final String jsonString = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);

		return new JSONObject(jsonString);
	}

	/**
	 * Compare a pair of {@link Map} objects for equivalence.
	 *
	 * @param expected The expected values
	 * @param actual The actual values received
	 */
	protected void compareMaps(final Map<String, String> expected, final Map<String, String> actual) {
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.keySet().containsAll(actual.keySet()));

		for (final Entry<String, String> expectedEntry : expected.entrySet()) {
			final String expectedKey = expectedEntry.getKey();
			final String expectedValue = expectedEntry.getValue();

			assertEquals(expectedValue, actual.get(expectedKey));
		}
	}
}
