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
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.OAuthQuery;
import com.sonoport.freesound.response.PendingUploads;
import com.sonoport.freesound.response.mapping.PendingUploadsMapper;

/**
 * Query class used to retrieve details of sounds that have been uploaded by the current user, but that require
 * additional steps to be performed before they are made publicly available.
 *
 * Full details of API endpoint: http://www.freesound.org/docs/api/resources_apiv2.html#pending-uploads-oauth2-required
 */
public class PendingUploadsQuery extends JSONResponseQuery<PendingUploads> implements OAuthQuery {

	/** Path to API endpoint. */
	private static final String PATH = "/sounds/pending_uploads/";

	/** OAuth2 token to present as credentials. */
	private final String ouathToken;

	/**
	 * @param oauthToken OAuth2 token to present as credentials
	 */
	public PendingUploadsQuery(final String oauthToken) {
		super(HTTPRequestMethod.GET, PATH, new PendingUploadsMapper());
		this.ouathToken = oauthToken;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

	@Override
	public String getOauthToken() {
		return ouathToken;
	}
}
