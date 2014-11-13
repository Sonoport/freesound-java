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

/**
 * Define what order search results should be returned as. Value is passed in the API call as a query parameter.
 */
public enum SortOrder {

	/** Sort by a relevance score returned by our search engine (default). */
	SCORE("score"),

	/** Sort by the duration of the sounds, longest sounds first. */
	DURATION_DESCENDING("duration_desc"),

	/**  Sort by the duration of the sounds, shortest sounds first. */
	DURATION_ASCENDING("duration_asc"),

	/** Sort by the date of when the sound was added. newest sounds first. */
	CREATED_DESCENDING("created_desc"),

	/** Sort by the date of when the sound was added. oldest sounds first. */
	CREATED_ASCENDING("created_asc"),

	/** Sort by the number of downloads, most downloaded sounds first. */
	DOWNLOADS_DESCENDING("downloads_desc"),

	/** Sort by the number of downloads, fewest downloads first. */
	DOWNLOADS_ASCENDING("downloads_asc"),

	/** Sort by the average rating given to the sounds, highest rated first. */
	RATING_DESCENDING("rating_desc"),

	/** Sort by the average rating given to the sounds, lowest rated first. */
	RATING_ASCENDING("rating_asc");

	/** The value that is passed as a parameter in the query to specify the sort order. */
	private String parameterValue;

	/**
	 * @param parameterValue Value to be passed as parameter for sort type
	 */
	private SortOrder(final String parameterValue) {
		this.parameterValue = parameterValue;
	}

	/**
	 * @return the parameterValue
	 */
	public String getParameterValue() {
		return parameterValue;
	}
}
