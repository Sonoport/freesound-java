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

/**
 * Representation of a filter that can be applied to a search. Multiple filters can be applied to a single query if
 * required.
 *
 * This is a very simple representation - it is down to the user to specify the filter details as a String using the
 * syntax specified by the freesound.org API. See documentation of the 'filter' parameter at
 * http://www.freesound.org/docs/api/resources_apiv2.html#text-search.
 */
public class SearchFilter {

	/** The name of the field to filter on. */
	private final String field;

	/** The filter value, expressed according to rules of API. */
	private final String value;

	/**
	 * @param field Field to filter on
	 * @param value The filter to apply
	 */
	public SearchFilter(final String field, final String value) {
		this.field = field;
		this.value = value;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((field == null) ? 0 : field.hashCode());
		result = (prime * result) + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SearchFilter other = (SearchFilter) obj;
		if (field == null) {
			if (other.field != null) {
				return false;
			}
		} else if (!field.equals(other.field)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
