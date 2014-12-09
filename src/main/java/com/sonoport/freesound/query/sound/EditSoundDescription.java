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

import com.sonoport.freesound.response.mapping.DetailStringMapper;

/**
 * Query class used to update the description associated with one of the user's previously uploaded sounds.
 */
public class EditSoundDescription extends AbstractSoundUploadQuery<String, EditSoundDescription> {

	/** Route parameter that will be replaced with sound identifier. */
	protected static final String SOUND_IDENTIFIER_ROUTE_PARAMETER = "sound_id";

	/** API endpoint path. */
	private static final String PATH = String.format("/sounds/{%s}/edit/", SOUND_IDENTIFIER_ROUTE_PARAMETER);

	/** Identifier of sound to modify. */
	private final int soundIdentifier;

	/**
	 * @param soundIdentifier Identifier of sound to modify
	 * @param oauthToken OAuth2 token to present
	 */
	public EditSoundDescription(final int soundIdentifier, final String oauthToken) {
		super(PATH, oauthToken, new DetailStringMapper());
		this.soundIdentifier = soundIdentifier;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_IDENTIFIER_ROUTE_PARAMETER, String.valueOf(soundIdentifier));

		return routeParams;
	}

}
