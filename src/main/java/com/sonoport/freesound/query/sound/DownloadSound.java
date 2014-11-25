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

import com.sonoport.freesound.query.BinaryResponseQuery;
import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.OAuthQuery;

/**
 * Query class used to download a given sound from freesound.org.
 */
public class DownloadSound extends BinaryResponseQuery implements OAuthQuery {

	/** The name of the path parameter used to determine the sound to retrieve. */
	protected static final String SOUND_IDENTIFIER_PARAMETER = "sound_id";

	/**
	 * The path to the freesound endpoint. Sound identifier is specified as a named parameter which will be populated
	 * at runtime by the <code>routeParam(String, String)</code> method.
	 */
	private static final String REQUEST_PATH = String.format("/sounds/{%s}/download/", SOUND_IDENTIFIER_PARAMETER);

	/** The identifier of the sound to retrieve. */
	private final int soundId;

	/** Bearer token used to authenticate request. */
	private final String oauthToken;

	/**
	 * @param soundId Identifier of sound to download
	 * @param oauthToken Token to use as authorisation
	 */
	public DownloadSound(final int soundId, final String oauthToken) {
		super(HTTPRequestMethod.GET, REQUEST_PATH);
		this.soundId = soundId;
		this.oauthToken = oauthToken;
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

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
