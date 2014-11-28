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

import org.json.JSONObject;

import com.sonoport.freesound.response.mapping.Mapper;

/**
 * Abstract representation of a {@link Query} that expects a JSON document returned from the HTTP call.
 *
 * @param <R> The DTO type to eventually return
 */
public abstract class JSONResponseQuery<R extends Object> extends Query<JSONObject, R> {

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path Endpoint to submit the query to
	 * @param resultsMapper {@link Mapper} to convert results
	 */
	protected JSONResponseQuery(
			final HTTPRequestMethod httpRequestMethod, final String path, final Mapper<JSONObject, R> resultsMapper) {
		super(httpRequestMethod, path, resultsMapper);
	}

	@Override
	protected String extractErrorMessage(final JSONObject freesoundResponse) {
		return freesoundResponse.getString("detail");
	}

}
