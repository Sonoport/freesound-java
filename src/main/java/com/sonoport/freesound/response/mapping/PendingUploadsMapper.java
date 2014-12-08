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

import org.json.JSONArray;
import org.json.JSONObject;

import com.sonoport.freesound.response.PendingUploads;

/**
 * Implementation of {@link Mapper} used to transform JSON response from the 'Pending Uploads' API endpoint into a
 * {@link PendingUploads} DTO.
 */
public class PendingUploadsMapper extends Mapper<JSONObject, PendingUploads> {

	/** Instance of {@link SoundMapper} used to transform details of sounds within the response. */
	private final SoundMapper soundMapper = new SoundMapper();

	@Override
	public PendingUploads map(final JSONObject source) {
		final PendingUploads pendingUploads = new PendingUploads();

		pendingUploads.setPendingDescription(
				parseArray(extractFieldValue(source, "pending_description", JSONArray.class)));

		pendingUploads.setPendingModeration(
				parseArray(extractFieldValue(source, "pending_moderation", JSONArray.class), soundMapper));

		pendingUploads.setPendingProcessing(
				parseArray(extractFieldValue(source, "pending_processing", JSONArray.class), soundMapper));

		return pendingUploads;
	}

}
