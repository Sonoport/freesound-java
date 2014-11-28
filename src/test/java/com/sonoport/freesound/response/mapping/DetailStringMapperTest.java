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

/**
 * Unit tests to ensure the correct operation of {@link DetailStringMapper}.
 */
public class DetailStringMapperTest extends MapperTest {

	/** Message to include in test response. */
	private static final String MESSAGE = "Success";

	/** JSON-formatted response to use in tests. */
	private static final JSONObject JSON_RESPONSE = new JSONObject(String.format("{ \"detail\": \"%s\" }", MESSAGE));

	/** Instance of {@link DetailStringMapper} to use in tests. */
	private final DetailStringMapper mapper = new DetailStringMapper();

	/**
	 * Ensure that correctly formatted response is correctly parsed.
	 */
	@Test
	public void convertJSONResponse() {
		final String message = mapper.map(JSON_RESPONSE);
		assertEquals(MESSAGE, message);
	}

	/**
	 * Ensure that passing in a null response does not cause any exceptions to be thrown.
	 */
	@Test
	public void nullResponse() {
		assertNull(mapper.map(null));
	}
}
