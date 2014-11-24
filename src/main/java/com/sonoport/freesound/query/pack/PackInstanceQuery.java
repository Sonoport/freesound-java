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

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.response.Pack;
import com.sonoport.freesound.response.mapping.PackMapper;

/**
 * Class used to represent a request for details regarding a single sound pack instance. The pack to retrieve is
 * identified by the pack ID.
 *
 * http://www.freesound.org/docs/api/resources_apiv2.html#pack-resources
 */
public class PackInstanceQuery extends JSONResponseQuery<Pack> {

	/** The route parameter that will be replaced with the pack identifier. */
	protected static final String PACK_IDENTIFIER_PARAMETER = "packIdentifier";

	/** Path to the pack endpoint in the API. */
	private static final String PATH = String.format("/packs/{%s}", PACK_IDENTIFIER_PARAMETER);

	/** The identifier of the pack to retrieve. */
	private final int packIdentifier;

	/**
	 * @param packIdentifier Identifier of the pack to retrieve
	 */
	public PackInstanceQuery(final int packIdentifier) {
		super(HTTPRequestMethod.GET, PATH, new PackMapper());

		this.packIdentifier = packIdentifier;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		final Map<String, String> routeParams = new HashMap<>();

		routeParams.put(PACK_IDENTIFIER_PARAMETER, String.valueOf(packIdentifier));

		return routeParams;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		return Collections.emptyMap();
	}

}
