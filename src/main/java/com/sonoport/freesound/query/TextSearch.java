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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.SoundResultsList;

/**
 * Class used to represent a Text Search of the freesound.org content library. The class presents a fluent API to allow
 * queries to be constructed more easily.
 *
 * Full details of the query can be found at http://www.freesound.org/docs/api/resources_apiv2.html#text-search.
 */
public class TextSearch extends Query<SoundResultsList> {

	/** The string to use as the search criteria. This value is used to populate the 'query' parameter on the request,
	 * so the full range of expressions permitted by the API can be specified. */
	private String searchString;

	/**
	 * No-arg constructor.
	 */
	public TextSearch() {
		super("/search/text/");
	}

	/**
	 * @param searchString The search string to use in the query
	 */
	public TextSearch(final String searchString) {
		this();
		this.searchString = searchString;
	}

	/**
	 * Add the search string in using a fluent approach.
	 *
	 * @param searchString The search string to use in the query
	 * @return The current query
	 */
	public TextSearch searchString(final String searchString) {
		this.searchString = searchString;
		return this;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> params = new HashMap<>();

		if (searchString != null) {
			params.put("query", searchString);
		}

		return params;
	}

	@Override
	protected SoundResultsList processResponse(final HttpResponse<JsonNode> freesoundResponse) {
		final JSONObject jsonResponse = freesoundResponse.getBody().getObject();

		final SoundResultsList resultsList = new SoundResultsList();

		final int resultCount = jsonResponse.getInt("count");
		resultsList.setCount(resultCount);

		final List<Sound> sounds = new LinkedList<>();
		final JSONArray soundResultsArray = jsonResponse.getJSONArray("results");
		for (int i = 0; i < soundResultsArray.length(); i++) {
			final JSONObject soundObject = soundResultsArray.getJSONObject(i);
			if (soundObject != null) {
				final Sound sound = convertSoundObject(soundObject);
				sounds.add(sound);
			}
		}
		resultsList.setSounds(sounds);

		return resultsList;
	}

	/**
	 * Convert the details of an individual sound from its {@link JSONObject} representation to a {@link Sound} object.
	 *
	 * @param soundObject JSON representation of a sound
	 * @return DTO representation of the sound
	 */
	private Sound convertSoundObject(final JSONObject soundObject) {
		final Sound sound = new Sound();

		sound.setId(soundObject.getInt("id"));
		sound.setUsername(soundObject.getString("username"));
		sound.setName(soundObject.getString("name"));
		sound.setLicense(soundObject.getString("license"));

		final Set<String> tags = new HashSet<>();
		final JSONArray tagsArray = soundObject.getJSONArray("tags");
		for (int i = 0; i < tagsArray.length(); i++) {
			final String tag = tagsArray.getString(i);
			if (tag != null) {
				tags.add(tag);
			}
		}

		return sound;
	}

}
