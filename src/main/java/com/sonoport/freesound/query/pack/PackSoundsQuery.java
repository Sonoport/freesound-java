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

import java.util.HashMap;
import java.util.Map;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.SoundPagingQuery;

/**
 * Query used to return details of all sounds in a given pack.
 *
 * API documentation at: http://www.freesound.org/docs/api/resources_apiv2.html#pack-sounds
 */
public class PackSoundsQuery extends SoundPagingQuery<PackSoundsQuery> {

	/** Route parameter to substitute with pack identifier. */
	protected static final String PACK_ID_ROUTE_PARAMETER = "pack_id";

	/** Path to API endpoint. */
	private static final String PATH = String.format("/packs/{%s}/sounds/", PACK_ID_ROUTE_PARAMETER);

	/** Identifier of pack to retrieve sounds for. */
	private final int packId;

	/**
	 * @param packId Identifier of pack to retrieve sounds for
	 */
	public PackSoundsQuery(final int packId) {
		super(HTTPRequestMethod.GET, PATH);
		this.packId = packId;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();
		routeParams.put(PACK_ID_ROUTE_PARAMETER, String.valueOf(packId));

		return routeParams;
	}

}
