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
package com.sonoport.freesound.query.sound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.Query;
import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.mapping.SoundMapper;

/**
 * Class used to represent a request for details regarding a single sound instance. The sound to retrieve is identified
 * by a numeric identifier.
 *
 * http://www.freesound.org/docs/api/resources_apiv2.html#sound-instance
 */
public class SoundInstanceQuery extends Query<Sound> {

	/** The name of the path parameter used to determine the sound to retrieve. */
	protected static final String SOUND_IDENTIFIER_PARAMETER = "sound_id";

	/**
	 * The path to the freesound endpoint. Sound identifier is specified as a named parameter which will be populated
	 * at runtime by the <code>routeParam(String, String)</code> method.
	 */
	private static final String REQUEST_PATH = String.format("/sounds/{%s}", SOUND_IDENTIFIER_PARAMETER);

	/** The identifier of the sound to retrieve. */
	private final int soundId;

	/**
	 * @param soundId The identifier of the sound
	 */
	public SoundInstanceQuery(final int soundId) {
		super(HTTPRequestMethod.GET, REQUEST_PATH, new SoundMapper());
		this.soundId = soundId;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParameters = new HashMap<>();
		routeParameters.put(SOUND_IDENTIFIER_PARAMETER, String.valueOf(soundId));

		return routeParameters;
	}

}
