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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

/**
 * Unit tests to ensure that the common code within {@link BinaryResponseQuery} is operating correctly.
 */
public class BinaryResponseQueryTest {

	/** The error message to use in tests. */
	private static final String ERROR_MESSAGE = "An error occured";

	/** The error message in the JSON format returned by the API. */
	private static final String ERROR_JSON = String.format("{ \"detail\" : \"%s\" }", ERROR_MESSAGE);

	/**
	 * Ensure that the error message is correctly extracted from the response received from the API. As we are expecting
	 * a binary response, we always receive an {@link InputStream} object as the result. In the event of an error, this
	 * will contain the JSON-formatted error message.
	 */
	@Test
	public void extractErrorMessageFromResponse() {
		final TestBinaryResponseQuery query = new TestBinaryResponseQuery();
		final InputStream is = new ByteArrayInputStream(ERROR_JSON.getBytes());

		final String errorMessage = query.extractErrorMessage(is);

		assertEquals(ERROR_MESSAGE, errorMessage);
	}

	/**
	 * Ensure that any non-JSON formatted responses received are handled, and a standard error message is returned.
	 */
	@Test
	public void nonJSONResponse() {
		final TestBinaryResponseQuery query = new TestBinaryResponseQuery();
		final InputStream is = new ByteArrayInputStream(ERROR_MESSAGE.getBytes());

		final String errorMessage = query.extractErrorMessage(is);

		assertEquals(BinaryResponseQuery.JSON_EXCEPTION_MESSAGE, errorMessage);
	}

	/**
	 * Ensure that any {@link IOException}s received are handled, and a standard error message is returned.
	 *
	 * @param mockInputStream Mocked {@link InputStream} object
	 * @exception Exception Any exceptions thrown in test
	 */
	@Test
	public void ioExceptionWhenProcessingInputStream(@Mocked final InputStream mockInputStream) throws Exception {
		new NonStrictExpectations() {
			{
				mockInputStream.read(); result = new IOException();
			}
		};

		final TestBinaryResponseQuery query = new TestBinaryResponseQuery();

		final String errorMessage = query.extractErrorMessage(mockInputStream);

		assertEquals(BinaryResponseQuery.IO_EXCEPTION_MESSAGE, errorMessage);
	}

	/**
	 * Simple subclass of {@link BinaryResponseQuery} to use in tests.
	 */
	private class TestBinaryResponseQuery extends BinaryResponseQuery {

		/**
		 * No-arg constructor.
		 */
		protected TestBinaryResponseQuery() {
			super(HTTPRequestMethod.GET, "");
		}

		@Override
		public Map<String, String> getRouteParameters() {
			return null;
		}

		@Override
		public Map<String, Object> getQueryParameters() {
			return null;
		}

	}
}
