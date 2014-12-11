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
package com.sonoport.freesound.response;

/**
 * Class representing the response to a call to the freesound.org API. Holds details of the HTTP response, plus the data
 * received converted into the appropriate DTO type. Alternatively, if the response was an HTTP error
 * (i.e. status >= 400), any error messages are converted into a String and stored in {@link Response#errorDetails}.
 *
 * @param <T> The DTO type to convert results into
 */
public class Response<T extends Object> {

	/** The HTTP response code received after making the call. */
	private final int responseStatus;

	/** String associated with the HTTP response code. */
	private final String responseStatusString;

	/** The details of any errors received from the HTTP call. This should generally only be populated if
	 * {@link Response#isErrorResponse()} returns true. */
	private String errorDetails;

	/** The results of the call, transformed into an appropriate DTO object. */
	private T results;

	/**
	 * @param responseStatus HTTP response status
	 * @param responseStatusString HTTP response status string
	 */
	public Response(final int responseStatus, final String responseStatusString) {
		this.responseStatus = responseStatus;
		this.responseStatusString = responseStatusString;
	}

	/**
	 * @return Whether the HTTP call made resulted in an error response
	 */
	public boolean isErrorResponse() {
		return responseStatus >= 400;
	}

	/**
	 * @return the errorDetails
	 */
	public String getErrorDetails() {
		return errorDetails;
	}

	/**
	 * @param errorDetails the errorDetails to set
	 */
	public void setErrorDetails(final String errorDetails) {
		this.errorDetails = errorDetails;
	}

	/**
	 * @return the results
	 */
	public T getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(final T results) {
		this.results = results;
	}

	/**
	 * @return the responseStatus
	 */
	public int getResponseStatus() {
		return responseStatus;
	}

	/**
	 * @return the responseStatusString
	 */
	public String getResponseStatusString() {
		return responseStatusString;
	}
}
