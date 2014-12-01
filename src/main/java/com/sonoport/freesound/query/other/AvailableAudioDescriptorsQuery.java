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
package com.sonoport.freesound.query.other;

import java.util.Collections;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.response.AudioDescriptors;
import com.sonoport.freesound.response.mapping.AudioDescriptorsMapper;

/**
 * Query used to retrieve all available audio descriptors used in content searches.
 *
 * API documentation at: http://www.freesound.org/docs/api/resources_apiv2.html#available-audio-descriptors.
 */
public class AvailableAudioDescriptorsQuery extends JSONResponseQuery<AudioDescriptors> {

	/** Path to API endpoint. */
	private static final String PATH = "/descriptors/";

	/**
	 * No-arg constructor.
	 */
	public AvailableAudioDescriptorsQuery() {
		super(HTTPRequestMethod.GET, PATH, new AudioDescriptorsMapper());
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

}
