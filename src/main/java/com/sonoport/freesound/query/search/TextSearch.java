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
package com.sonoport.freesound.query.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.SoundPagingQuery;

/**
 * Class used to represent a Text Search of the freesound.org content library. The class presents a fluent API to allow
 * queries to be constructed more easily.
 *
 * Full details of the query can be found at http://www.freesound.org/docs/api/resources_apiv2.html#text-search.
 */
public class TextSearch extends SoundPagingQuery<TextSearch> {

	/** Name of the parameter to pass the names of fields to include in responses as. */
	protected static final String FIELDS_PARAMETER = "fields";

	/** The name of the query parameter to pass the search string over as. */
	private static final String SEARCH_STRING_PARAMETER = "query";

	/** The name of the query parameter to pass the sort order over as. */
	private static final String SORT_ORDER_PARAMETER = "sort";

	/** The name of the parameter to pass the 'group by pack' flag over as. */
	private static final String GROUP_BY_PACK_PARAMETER = "group_by_pack";

	/** The string to use as the search criteria. This value is used to populate the 'query' parameter on the request,
	 * so the full range of expressions permitted by the API can be specified. */
	private String searchString;

	/** The order in which to request results are sorted. The enum specified here contains the appropriate value to pass
	 * in the HTTP call. */
	private SortOrder sortOrder;

	/** Collection of filters that should be applied as part of the query. */
	private Set<SearchFilter> filters;

	/** Whether to group results by the pack to which they belong. */
	private Boolean groupByPack;

	/**
	 * No-arg constructor.
	 */
	public TextSearch() {
		super(HTTPRequestMethod.GET, "/search/text/");
	}

	/**
	 * @param searchString The search string to use in the query
	 */
	public TextSearch(final String searchString) {
		this();
		this.searchString = searchString;
	}

	/**
	 * Add the search string using the Fluent API approach.
	 *
	 * @param searchString The search string to use in the query
	 * @return The current query
	 */
	public TextSearch searchString(final String searchString) {
		this.searchString = searchString;
		return this;
	}

	/**
	 * Add a filter to the query using the Fluent API approach.
	 *
	 * @param filter The filter to add
	 * @return The current query
	 */
	public TextSearch filter(final SearchFilter filter) {
		if (filters == null) {
			filters = new HashSet<SearchFilter>();
		}
		filters.add(filter);

		return this;
	}

	/**
	 * Specify whether sound results should be grouped into the packs to which they belong, using the Fluent API
	 * approach.
	 *
	 * @param groupByPack Whether sounds should be grouped
	 * @return The current query
	 */
	public TextSearch groupByPack(final boolean groupByPack) {
		this.groupByPack = Boolean.valueOf(groupByPack);
		return this;
	}

	/**
	 * Add a sort order to the query, using the Fluent API approach.
	 *
	 * @param sortOrder The sort order to apply
	 * @return The current query
	 */
	public TextSearch sortOrder(final SortOrder sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> params = super.getQueryParameters();

		if (searchString != null) {
			params.put(SEARCH_STRING_PARAMETER, searchString);
		}

		if (sortOrder != null) {
			params.put(SORT_ORDER_PARAMETER, sortOrder.getParameterValue());
		}

		if (groupByPack != null) {
			String numericBooleanValue = "0";
			if (groupByPack) {
				numericBooleanValue = "1";
			}

			params.put(GROUP_BY_PACK_PARAMETER, numericBooleanValue);
		}

		if ((filters != null) && !filters.isEmpty()) {
			final StringBuilder filterString = new StringBuilder();

			for (final SearchFilter filter : filters) {
				filterString.append(String.format("%s:%s", filter.getField(), filter.getValue()));
				filterString.append(' ');
			}

			params.put("filter", filterString.toString().trim());
		}

		return params;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

}
