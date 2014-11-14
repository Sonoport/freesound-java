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

import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.SoundResultsList;

/**
 * Map the freesound.org JSON representation of search results to a {@link SoundResultsList} DTO.
 */
public class SoundResultsListMapper extends Mapper<JSONObject, SoundResultsList> {

	@Override
	public SoundResultsList map(final JSONObject source) {
		final SoundResultsList resultsList = new SoundResultsList();

		resultsList.setCount(extractFieldValue(source, "count", Integer.class));
		resultsList.setNextPageURI(extractFieldValue(source, "next", String.class));
		resultsList.setPreviousPageURI(extractFieldValue(source, "previous", String.class));

		final List<Sound> sounds = new LinkedList<>();
		final JSONArray soundResultsArray = extractFieldValue(source, "results", JSONArray.class);

		if (soundResultsArray != null) {
			final SoundMapper soundMapper = new SoundMapper();
			for (int i = 0; i < soundResultsArray.length(); i++) {
				if (!soundResultsArray.isNull(i)) {
					final JSONObject soundObject = soundResultsArray.getJSONObject(i);
					if ((soundObject != null)) {
						final Sound sound = soundMapper.map(soundObject);
						sounds.add(sound);
					}
				}
			}
		}
		resultsList.setSounds(sounds);

		return resultsList;
	}

}
