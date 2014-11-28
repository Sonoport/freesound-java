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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.mapping.PagingResponseMapper;
import com.sonoport.freesound.response.mapping.SoundMapper;

/**
 * Convenience class for dealing with {@link PagingQuery} requests that return a list of {@link Sound}s. There are a
 * number of common query elements that can be associated with such queries (fields to include, for example), so these
 * are encapsulated in this class.
 *
 * @param <Q> The type of the {@link SoundPagingQuery} (required to implement Fluent API elements)
 */
public abstract class SoundPagingQuery<Q extends SoundPagingQuery<Q>> extends PagingQuery<Q, Sound> {

	/** Name of parameter to include the list of fields to return, if specified. */
	public static final String FIELDS_PARAMETER = "fields";

	/** The fields to retrieve as part of the query. If values are specified here, only those fields will be returned.
	 * If no values are specified, freesound will return a default set. */
	private Set<String> fields;

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path The URI path to the API endpoint
	 */
	protected SoundPagingQuery(final HTTPRequestMethod httpRequestMethod, final String path) {
		super(httpRequestMethod, path, new PagingResponseMapper<>(new SoundMapper()));
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> params = super.getQueryParameters();

		if ((fields != null) && !fields.isEmpty()) {
			final StringBuilder fieldsString = new StringBuilder();
			for (final String field : fields) {
				fieldsString.append(field.trim());
				fieldsString.append(',');
			}
			fieldsString.deleteCharAt(fieldsString.lastIndexOf(","));

			params.put(FIELDS_PARAMETER, fieldsString.toString());
		}

		return params;
	}

	/**
	 * Specify a field to return in the results using the Fluent API approach. Users may specify this method multiple
	 * times to define the collection of fields they want returning, and/or use
	 * {@link SoundPagingQuery#includeFields(Set)} to  define them as a batch.
	 *
	 * @param field The field to include in the results
	 * @return The current query
	 */
	@SuppressWarnings("unchecked")
	public Q includeField(final String field) {
		if (this.fields == null) {
			this.fields = new HashSet<>();
		}

		if (field != null) {
			this.fields.add(field);
		}

		return (Q) this;
	}

	/**
	 * Specify the set of fields to return in the results. Defined using the Fluent API approach.
	 *
	 * @param fields The fields to return
	 * @return The current query
	 */
	@SuppressWarnings("unchecked")
	public Q includeFields(final Set<String> fields) {
		if (this.fields == null) {
			this.fields = new HashSet<>();
		}

		if (fields != null) {
			this.fields.addAll(fields);
		}

		return (Q) this;
	}
}