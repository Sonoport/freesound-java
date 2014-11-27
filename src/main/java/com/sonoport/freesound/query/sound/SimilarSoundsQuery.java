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

import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.SoundPagingQuery;

/**
 * Query used to retrieve a list of sounds similar to the on specified.
 *
 * API documentation at: http://www.freesound.org/docs/api/resources_apiv2.html#similar-sounds
 */
public class SimilarSoundsQuery extends SoundPagingQuery<SimilarSoundsQuery> {

	/** Route parameter to be replaced with the identifier to retrieve similar sounds for. */
	protected static final String SOUND_ID_ROUTE_PARAMETER = "sound_id";

	/** Path to the API endpoint. */
	private static final String PATH = String.format("/sounds/{%s}/similar/", SOUND_ID_ROUTE_PARAMETER);

	/** Identifier of the sound to retrieve similar sounds for. */
	private final int soundId;

	/**
	 * @param soundId Identifier of the sound to retrieve similar sounds for
	 */
	public SimilarSoundsQuery(final int soundId) {
		super(HTTPRequestMethod.GET, PATH);
		this.soundId = soundId;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_ID_ROUTE_PARAMETER, String.valueOf(soundId));

		return routeParams;
	}

}
