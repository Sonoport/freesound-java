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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.response.SoundResultsList;
import com.sonoport.freesound.response.mapping.SoundResultsListMapper;

/**
 * Class used to represent a Text Search of the freesound.org content library. The class presents a fluent API to allow
 * queries to be constructed more easily.
 *
 * Full details of the query can be found at http://www.freesound.org/docs/api/resources_apiv2.html#text-search.
 */
public class TextSearch extends PagingQuery<TextSearch, SoundResultsList> {

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

	/** The fields to retrieve as part of the query. If values are specified here, only those fields will be returned.
	 * If no values are specified, freesound will return a default set. */
	private Set<String> fields;

	/** Whether to group results by the pack to which they belong. */
	private Boolean groupByPack;

	/**
	 * No-arg constructor.
	 */
	public TextSearch() {
		super("/search/text/", new SoundResultsListMapper());
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

	/**
	 * Specify a field to return in the results using the Fluent API approach. Users may specify this method multiple
	 * times to define the collection of fields they want returning, and/or use {@link TextSearch#includeFields(Set)} to
	 * define them as a batch.
	 *
	 * @param field The field to include in the results
	 * @return The current query
	 */
	public TextSearch includeField(final String field) {
		if (this.fields == null) {
			this.fields = new HashSet<>();
		}

		if (field != null) {
			this.fields.add(field);
		}

		return this;
	}

	/**
	 * Specify the set of fields to return in the results. Defined using the Fluent API approach.
	 *
	 * @param fields The fields to return
	 * @return The current query
	 */
	public TextSearch includeFields(final Set<String> fields) {
		if (this.fields == null) {
			this.fields = new HashSet<>();
		}

		if (fields != null) {
			this.fields.addAll(fields);
		}

		return this;
	}

	@Override
	public Map<String, Object> getRequestParameters() {
		final Map<String, Object> params = new HashMap<>();

		if (searchString != null) {
			params.put(SEARCH_STRING_PARAMETER, searchString);
		}

		if (sortOrder != null) {
			params.put(SORT_ORDER_PARAMETER, sortOrder.getParameterValue());
		}

		if ((fields != null) && !fields.isEmpty()) {
			final StringBuilder fieldsString = new StringBuilder();
			for (final String field : fields) {
				fieldsString.append(field);
				fieldsString.append(',');
			}
			fieldsString.deleteCharAt(fieldsString.lastIndexOf(","));

			params.put("fields", fieldsString.toString());
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
	public boolean hasNextPage() {
		final SoundResultsList results = getResults();
		return results.getNextPageURI() != null;
	}

	@Override
	public boolean hasPreviousPage() {
		final SoundResultsList results = getResults();
		return results.getPreviousPageURI() != null;
	}

}
