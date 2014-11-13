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

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

/**
 * Representation of a query of the freesound API, encapsulating the common elements the different types of calls.
 * Implementation details are delegated to extending sub-classes.
 *
 * @param <R> The data type of the results we'll return
 */
public abstract class Query<R extends Object> {

	/** The URI path to the query endpoint. */
	private final String path;

	/** The HTTP response code received after making the call. */
	private int httpResponseCode;

	/** The details of any errors received from the HTTP call. This should generally only be populated if
	 * {@link Query#isErrorResponse()} returns true. */
	private String errorDetails;

	/** The results of the call, transformed into an appropriate DTO object. */
	private R results;

	/**
	 * @param path Endpoint to submit the query to
	 */
	protected Query(final String path) {
		this.path = path;
	}

	/**
	 * @return Query parameters and values to include in the query
	 */
	public abstract Map<String, Object> getQueryParameters();

	/**
	 * Set the raw response received from the HTTP call. Contents are passed on to sub-classes for mapping to DTOs.
	 *
	 * @param freesoundResponse The response received from the HTTP call
	 */
	public final void setResponse(final HttpResponse<JsonNode> freesoundResponse) {
		httpResponseCode = freesoundResponse.getCode();

		if (isErrorResponse()) {
			final JSONObject body = freesoundResponse.getBody().getObject();
			errorDetails = body.getString("detail");
		} else {
			results = processResponse(freesoundResponse);
		}
	}

	/**
	 * @return Whether the HTTP call made resulted in an error response
	 */
	public boolean isErrorResponse() {
		return httpResponseCode >= 400;
	}

	/**
	 * Process the HTTP response received from the API into the required result DTO format.
	 *
	 * @param freesoundResponse The response received from the HTTP call
	 * @return DTO representation of the results
	 */
	protected abstract R processResponse(HttpResponse<JsonNode> freesoundResponse);

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the results
	 */
	public R getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(final R results) {
		this.results = results;
	}

	/**
	 * @return the httpResponseCode
	 */
	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	/**
	 * @return the errorDetails
	 */
	public String getErrorDetails() {
		return errorDetails;
	}

}
