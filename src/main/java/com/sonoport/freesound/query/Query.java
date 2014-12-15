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

import java.util.Map;

import com.sonoport.freesound.response.Response;
import com.sonoport.freesound.response.mapping.Mapper;

/**
 * Representation of a query of the freesound API, encapsulating the common elements the different types of calls.
 * Implementation details are delegated to extending sub-classes.
 *
 * @param <S> The source data type we expect to receive as raw data
 * @param <R> The data type of the results we'll return
 */
public abstract class Query<S extends Object, R extends Object> {

	/** The HTTP method to use in making the call to the API. */
	private final HTTPRequestMethod httpRequestMethod;

	/** The URI path to the query endpoint. */
	private final String path;

	/** {@link Mapper} used to convert results received from freesound into appropriate DTO type. */
	private final Mapper<S, R> resultsMapper;

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path Endpoint to submit the query to
	 * @param resultsMapper {@link Mapper} to convert results
	 */
	protected Query(
			final HTTPRequestMethod httpRequestMethod,
			final String path,
			final Mapper<S, R> resultsMapper) {
		this.httpRequestMethod = httpRequestMethod;
		this.path = path;
		this.resultsMapper = resultsMapper;
	}

	/**
	 * @return Route parameters used to construct the URI
	 */
	public abstract Map<String, String> getRouteParameters();

	/**
	 * @return Query parameters and values to include in the query
	 */
	public abstract Map<String, Object> getQueryParameters();

	/**
	 * Set the raw response received from the HTTP call. Contents are passed on to sub-classes for mapping to DTOs.
	 *
	 * @param httpResponseCode The HTTP response code received
	 * @param httpResponseStatusString The text associated with the HTTP response code
	 * @param freesoundResponse The response received from the HTTP call
	 *
	 * @return Results of the query
	 */
	public Response<R> processResponse(
			final int httpResponseCode, final String httpResponseStatusString, final S freesoundResponse) {
		final Response<R> response = new Response<>(httpResponseCode, httpResponseStatusString);

		if (response.isErrorResponse()) {
			response.setErrorDetails(extractErrorMessage(freesoundResponse));
		} else {
			response.setResults(resultsMapper.map(freesoundResponse));
		}

		return response;
	}

	/**
	 * Extract the error message received in the event the request was not successful.
	 *
	 * @param freesoundResponse The response received
	 * @return The error message
	 */
	protected abstract String extractErrorMessage(S freesoundResponse);

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the httpRequestMethod
	 */
	public HTTPRequestMethod getHttpRequestMethod() {
		return httpRequestMethod;
	}

	/**
	 * @return the resultsMapper
	 */
	protected Mapper<S, R> getResultsMapper() {
		return resultsMapper;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		sb.append(getClass().getSimpleName()).append(": ");
		sb.append(String.format("Route Parameters: %s, ", getRouteParameters()));
		sb.append(String.format("Query Parameters: %s", getQueryParameters()));

		return sb.toString();
	}

}
