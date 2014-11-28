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
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.response.Comment;
import com.sonoport.freesound.response.mapping.CommentMapper;
import com.sonoport.freesound.response.mapping.PagingResponseMapper;

/**
 * Query class used to retrieve comments associated with a given sound.
 *
 * API documentation located at: http://www.freesound.org/docs/api/resources_apiv2.html#sound-comments
 */
public class SoundCommentsQuery extends PagingQuery<SoundCommentsQuery, Comment> {

	/** Name of the route parameter to substitute sound identifier for. */
	protected static final String SOUND_ID_ROUTE_PARAMETER = "sound_id";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/sounds/{%s}/comments/", SOUND_ID_ROUTE_PARAMETER);

	/** Identifier of the sound to retrieve comments for. */
	private final int soundId;

	/**
	 * @param soundId Identifier of the sound to retrieve comments for
	 */
	public SoundCommentsQuery(final int soundId) {
		super(HTTPRequestMethod.GET, PATH, new PagingResponseMapper<>(new CommentMapper()));
		this.soundId = soundId;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(SOUND_ID_ROUTE_PARAMETER, String.valueOf(soundId));

		return routeParams;
	}

}
