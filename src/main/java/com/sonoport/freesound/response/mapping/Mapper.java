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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract class defining common functionality required when converting objects of different types. In general, this
 * means JSON objects received from calls to the freesound.org API into the DTOs returned by this library.
 *
 * @param <S> The source type we are converting from
 * @param <R> The type we are converting to
 */
public abstract class Mapper<S extends Object, R extends Object> {

	/** The date/time format used by freesound. */
	private static final String FREESOUND_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Perform the conversion between the two specified types.
	 *
	 * @param source The object we are converting from
	 * @return The converted object
	 */
	public abstract R map(S source);

	/**
	 * Extract a named value from a {@link JSONObject}. This method checks whether the value exists and is not an
	 * instance of <code>JSONObject.NULL</code>.
	 *
	 * @param jsonObject The {@link JSONObject} being processed
	 * @param field The field to retrieve
	 * @param fieldType The data type of the field
	 * @return The field value (or null if not found)
	 *
	 * @param <T> The data type to return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends Object> T extractFieldValue(
			final JSONObject jsonObject, final String field, final Class<T> fieldType) {
		T fieldValue = null;
		if ((jsonObject != null) && jsonObject.has(field) && !jsonObject.isNull(field)) {
			try {
				if (fieldType == String.class) {
					fieldValue = (T) jsonObject.getString(field);
				} else if (fieldType == Integer.class) {
					fieldValue = (T) Integer.valueOf(jsonObject.getInt(field));
				} else if (fieldType == Long.class) {
					fieldValue = (T) Long.valueOf(jsonObject.getLong(field));
				} else if (fieldType == Float.class) {
					fieldValue = (T) Float.valueOf(Double.toString(jsonObject.getDouble(field)));
				} else if (fieldType == JSONArray.class) {
					fieldValue = (T) jsonObject.getJSONArray(field);
				} else if (fieldType == JSONObject.class) {
					fieldValue = (T) jsonObject.getJSONObject(field);
				} else {
					fieldValue = (T) jsonObject.get(field);
				}
			} catch (final JSONException | ClassCastException e) {
				// TODO Log a warning
			}
		}

		return fieldValue;
	}

	/**
	 * Transform a JSON dictionary into a {@link Map} of key-value pairs.
	 *
	 * @param jsonDictionaryObject The dictionary object to convert
	 * @return {@link Map} of values
	 */
	protected Map<String, String> parseDictionary(final JSONObject jsonDictionaryObject) {
		final Map<String, String> dictionaryAsMap = new HashMap<>();
		if (jsonDictionaryObject != null) {
			for (final String key : JSONObject.getNames(jsonDictionaryObject)) {
				dictionaryAsMap.put(key, extractFieldValue(jsonDictionaryObject, key, String.class));
			}
		}

		return dictionaryAsMap;
	}

	/**
	 * Transform a JSON Array into a {@link List}.
	 *
	 * @param jsonArray The {@link JSONArray} to convert
	 * @return List of values
	 */
	protected List<String> parseArray(final JSONArray jsonArray) {
		final List<String> arrayContents = new ArrayList<>();

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				final String element = jsonArray.getString(i);
				if (element != null) {
					arrayContents.add(element);
				}
			}
		}

		return arrayContents;
	}

	/**
	 * Parse a date from a {@link String} to a {@link Date} object, assuming the freesound standard format.
	 *
	 * @param dateString The string to convert
	 * @return {@link Date} representation
	 */
	protected Date parseDate(final String dateString) {
		Date date = null;
		if (dateString != null) {
			try {
				final SimpleDateFormat dateFormat = new SimpleDateFormat(FREESOUND_DATE_FORMAT);
				dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

				date = dateFormat.parse(dateString);
			} catch (final ParseException e) {
				// TODO Log a warning
			}
		}

		return date;
	}
}
