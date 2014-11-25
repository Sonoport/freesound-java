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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import com.sonoport.freesound.response.mapping.InputStreamMapper;

/**
 * Abstract representation of a {@link Query} that returns a binary response. These queries will return an
 * {@link InputStream} object representing the binary content returned by the API - it is the responsibility of the
 * caller to properly close this.
 */
public abstract class BinaryResponseQuery extends Query<InputStream, InputStream> {

	/** Error message to return if a non-JSON-formatted error response is received. */
	protected static final String JSON_EXCEPTION_MESSAGE = "A non-JSON formatted error response was received";

	/** Error message to return if there is a problem processing the {@link InputStream} received. */
	protected static final String IO_EXCEPTION_MESSAGE = "An error occurred processing the error response received";

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path Endpoint to submit the query to
	 */
	protected BinaryResponseQuery(final HTTPRequestMethod httpRequestMethod, final String path) {
		super(httpRequestMethod, path, new InputStreamMapper());
	}

	@Override
	protected String extractErrorMessage(final InputStream freesoundResponse) {
		try {
			final BufferedReader streamReader = new BufferedReader(new InputStreamReader(freesoundResponse, "UTF-8"));
			final StringBuilder responseStrBuilder = new StringBuilder();

			String streamContent;
			while ((streamContent = streamReader.readLine()) != null) {
				responseStrBuilder.append(streamContent);
			}

			final JSONObject jsonResponse = new JSONObject(responseStrBuilder.toString());

			return jsonResponse.getString("detail");
		} catch (final JSONException e) {
			e.printStackTrace();
			return JSON_EXCEPTION_MESSAGE;
		} catch (final IOException e) {
			e.printStackTrace();
			return IO_EXCEPTION_MESSAGE;
		}
	}

}
