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

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * {@link Mapper} implementation used to parse JSON representation of a list of items that may spread over multiple
 * pages.
 *
 * @param <I> The data type of the individual items in the list
 */
public class PagingResponseMapper<I extends Object> extends Mapper<JSONObject, List<I>> {

	/** {@link Mapper} used to process the individual items in the list. */
	private final Mapper<JSONObject, I> itemMapper;

	/**
	 * @param itemMapper {@link Mapper} used to process the individual items
	 */
	public PagingResponseMapper(final Mapper<JSONObject, I> itemMapper) {
		this.itemMapper = itemMapper;
	}

	@Override
	public List<I> map(final JSONObject source) {
		final List<I> items = new LinkedList<>();
		final JSONArray resultsArray = extractFieldValue(source, "results", JSONArray.class);

		if (resultsArray != null) {
			for (int i = 0; i < resultsArray.length(); i++) {
				if (!resultsArray.isNull(i)) {
					final JSONObject jsonObject = resultsArray.getJSONObject(i);
					if ((jsonObject != null)) {
						final I item = itemMapper.map(jsonObject);
						items.add(item);
					}
				}
			}
		}

		return items;
	}

	/**
	 * Retrieve the total number of results for the query from the JSON message.
	 *
	 * @param source JSON response from freesound
	 * @return Total number of results
	 */
	public Integer extractCount(final JSONObject source) {
		return extractFieldValue(source, "count", Integer.class);
	}

	/**
	 * Retrieve the URI of the next page of results (if any) for the query from the JSON message.
	 *
	 * @param source JSON response from freesound
	 * @return Next page URI
	 */
	public String extractNextPageURI(final JSONObject source) {
		return extractFieldValue(source, "next", String.class);
	}

	/**
	 * Retrieve the URI of the previous page of results (if any) for the query from the JSON message.
	 *
	 * @param source JSON response from freesound
	 * @return Previous page URI
	 */
	public String extractPreviousPageURI(final JSONObject source) {
		return extractFieldValue(source, "previous", String.class);
	}
}
