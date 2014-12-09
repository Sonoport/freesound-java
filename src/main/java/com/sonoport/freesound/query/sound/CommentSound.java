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
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.OAuthQuery;
import com.sonoport.freesound.response.mapping.DetailStringMapper;

/**
 * Query class that allows users to add comments to sounds.
 */
public class CommentSound extends JSONResponseQuery<String> implements OAuthQuery {

	/** Name of route parameter to be replaced with teh sound identifier. */
	protected static final String SOUND_IDENTIFIER_ROUTE_PARAMETER = "sound_id";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/sounds/{%s}/comment/", SOUND_IDENTIFIER_ROUTE_PARAMETER);

	/** Parameter to pass comment through as. */
	protected static final String COMMENT_PARAMETER_NAME = "comment";

	/** Identifier of sound to add comment to. */
	private final int soundIdentifier;

	/** Comment to add. */
	private final String comment;

	/** OAuth taken to present. */
	private final String oauthToken;

	/**
	 * @param soundIdentifier Identifier of sound
	 * @param comment Comment to add
	 * @param oauthToken Token to present
	 */
	public CommentSound(final int soundIdentifier, final String comment, final String oauthToken) {
		super(HTTPRequestMethod.POST, PATH, new DetailStringMapper());

		this.soundIdentifier = soundIdentifier;
		this.comment = comment;
		this.oauthToken = oauthToken;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_IDENTIFIER_ROUTE_PARAMETER, String.valueOf(soundIdentifier));

		return routeParams;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put(COMMENT_PARAMETER_NAME, comment);

		return queryParams;
	}

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
