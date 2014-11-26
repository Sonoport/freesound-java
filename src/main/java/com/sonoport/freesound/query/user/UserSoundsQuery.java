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
package com.sonoport.freesound.query.user;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.mapping.PagingResponseMapper;
import com.sonoport.freesound.response.mapping.SoundMapper;

/**
 * Query used to retrieve all the sounds owned by a particular user.
 */
public class UserSoundsQuery extends PagingQuery<UserSoundsQuery, Sound> {

	/** Name of the parameter in the path to replace with the username of owner of the sounds to retrieve. */
	protected static final String USERNAME_ROUTE_PARAMETER = "username";

	/** Path to the API endpoint. */
	private static final String PATH = String.format("/users/{%s}/sounds/", USERNAME_ROUTE_PARAMETER);

	/** Name of parameter to include the list of fields to return, if specified. */
	protected static final String FIELDS_PARAMETER = "fields";

	/** The username of the owner of the sounds to retrieve. */
	private final String username;

	/** The fields to retrieve as part of the query. If values are specified here, only those fields will be returned.
	 * If no values are specified, freesound will return a default set. */
	private Set<String> fields;

	/**
	 * @param username Username of the owner of the sounds to retrieve
	 */
	public UserSoundsQuery(final String username) {
		super(HTTPRequestMethod.GET, PATH, new PagingResponseMapper<>(new SoundMapper()));
		this.username = username;
	}

	@Override
	protected Map<String, Object> getRequestParameters() {
		final Map<String, Object> params = new HashMap<>();

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

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(USERNAME_ROUTE_PARAMETER, username);

		return routeParams;
	}

	/**
	 * Specify a field to return in the results using the Fluent API approach. Users may specify this method multiple
	 * times to define the collection of fields they want returning, and/or use
	 * {@link UserSoundsQuery#includeFields(Set)} to  define them as a batch.
	 *
	 * @param field The field to include in the results
	 * @return The current query
	 */
	public UserSoundsQuery includeField(final String field) {
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
	public UserSoundsQuery includeFields(final Set<String> fields) {
		if (this.fields == null) {
			this.fields = new HashSet<>();
		}

		if (fields != null) {
			this.fields.addAll(fields);
		}

		return this;
	}
}
