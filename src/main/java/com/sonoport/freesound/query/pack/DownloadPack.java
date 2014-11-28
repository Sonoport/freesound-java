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
package com.sonoport.freesound.query.pack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.BinaryResponseQuery;
import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.OAuthQuery;

/**
 * Class used to download all sounds within a given pack. The response from the API is a ZIP file containing the sounds.
 *
 * API documentation at: http://www.freesound.org/docs/api/resources_apiv2.html#download-pack-oauth2-required
 */
public class DownloadPack extends BinaryResponseQuery implements OAuthQuery {

	/** Route parameter to replace with pack identifier. */
	protected static final String PACK_ID_ROUTE_PARAMETER = "pack_id";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/packs/{%s}/download/", PACK_ID_ROUTE_PARAMETER);

	/** Identifier of pack to download. */
	private final int packId;

	/** OAuth2 Access token to present with request. */
	private final String oauthToken;

	/**
	 * @param packId Identifier of pack to download
	 * @param oauthToken OAuth2 Access token to present with request
	 */
	public DownloadPack(final int packId, final String oauthToken) {
		super(HTTPRequestMethod.GET, PATH);
		this.packId = packId;
		this.oauthToken = oauthToken;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(PACK_ID_ROUTE_PARAMETER, String.valueOf(packId));

		return routeParams;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

	@Override
	public String getOauthToken() {
		return oauthToken;
	}

}
