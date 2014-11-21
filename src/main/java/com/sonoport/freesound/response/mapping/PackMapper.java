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
package com.sonoport.freesound.response.mapping;

import org.json.JSONObject;

import com.sonoport.freesound.response.Pack;

/**
 * {@link Mapper} class used to transform JSON representation of a sound pack as received from freesound, into a
 * {@link Pack} DTO.
 */
public class PackMapper extends Mapper<JSONObject, Pack> {

	@Override
	public Pack map(final JSONObject source) {
		final Pack pack = new Pack();

		pack.setId(extractFieldValue(source, "id", Integer.class));
		pack.setUrl(extractFieldValue(source, "url", String.class));
		pack.setDescription(extractFieldValue(source, "description", String.class));
		pack.setCreated(parseDate(extractFieldValue(source, "created", String.class)));
		pack.setName(extractFieldValue(source, "name", String.class));
		pack.setUsername(extractFieldValue(source, "username", String.class));
		pack.setNumberOfSounds(extractFieldValue(source, "num_sounds", Integer.class));
		pack.setSoundsURI(extractFieldValue(source, "sounds", String.class));
		pack.setNumberOfDownloads(extractFieldValue(source, "num_downloads", Integer.class));

		return pack;
	}

}
