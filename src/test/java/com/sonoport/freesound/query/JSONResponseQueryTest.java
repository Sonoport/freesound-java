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
package com.sonoport.freesound.query;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

/**
 * Unit tests to ensure the common code in {@link JSONResponseQuery} class operates correctly.
 *
 * @param <T> The subclass of {@link JSONResponseQuery} under test
 */
public abstract class JSONResponseQueryTest<T extends JSONResponseQuery<?>> extends QueryTest<T> {

	/** The error message to use in tests. */
	private static final String ERROR_MESSAGE = "An error occured";

	/** The error message in the JSON format returned by the API. */
	private static final JSONObject ERROR_JSON =
			new JSONObject(String.format("{ \"detail\" : \"%s\" }", ERROR_MESSAGE));

	/**
	 * Ensure that the error message is correctly extracted from an error response.
	 */
	@Test
	public void extractErrorMessageFromJSON() {
		final T query = newQueryInstance();

		final String errorMessage = query.extractErrorMessage(ERROR_JSON);

		assertEquals(ERROR_MESSAGE, errorMessage);
	}
}
