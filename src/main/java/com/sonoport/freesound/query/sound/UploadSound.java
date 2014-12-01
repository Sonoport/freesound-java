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

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.response.UploadedSoundDetails;
import com.sonoport.freesound.response.mapping.UploadedSoundDetailsMapper;

/**
 * Query used to upload a sound file to Freesound.
 *
 * API documentation: http://www.freesound.org/docs/api/resources_apiv2.html#upload-sound-oauth2-required
 */
public class UploadSound extends JSONResponseQuery<UploadedSoundDetails> {

	/** Parameter to pass the sound file through as. */
	protected static final String SOUND_FILE_PARAMETER_NAME = "audioFile";

	/** Parameter to pass the sound description through as. */
	protected static final String DESCRIPTION_PARAMETER_NAME = "description";

	/** Parameter to pass the license through as. */
	protected static final String LICENSE_PARAMETER_NAME = "license";

	/** Parameter to pass the tags through as. */
	protected static final String TAGS_PARAMETER_NAME = "tags";

	/** Parameter to pass the geotag through as. */
	private static final String GEOTAG_PARAMETER_NAME = "geotag";

	/** Parameter to pass the pack name through as. */
	protected static final String PACK_PARAMETER_NAME = "pack";

	/** Path to API endpoint. */
	private static final String PATH = "/sounds/upload/";

	/** The file to upload. */
	private final File soundFile;

	/** The name that will be given to the sound. If not provided, filename will be used. */
	private String name;

	/** The tags that will be assigned to the sound. */
	private final Set<String> tags;

	/** A textual description of the sound. */
	private final String description;

	/** The license of the sound. */
	private final String license;

	/** The name of the pack where the sound should be included. If user has created no such pack with that name, a new
	 * one will be created. */
	private String pack;

	/** Geotag information for the sound. */
	private String geotag;

	/**
	 * Simplest means of uploading a sound - will have default details associated with it. Further details can be added
	 * using the Fluent API methods, or at a later time by using the Describe Sound query type.
	 *
	 * @param soundFile The sound file to upload
	 */
	public UploadSound(final File soundFile) {
		this(soundFile, null, null, null);
	}

	/**
	 * More complete means of uploading sound. The three additional fields are mandatory together when one is specified.
	 * Further details can be added using the Fluent API methods.
	 *
	 * @param soundFile The sound file to upload
	 * @param description Description of the sound
	 * @param license License attached to sound
	 * @param tags Tags associated with sound
	 */
	public UploadSound(final File soundFile, final String description, final String license, final Set<String> tags) {
		super(HTTPRequestMethod.POST, PATH, new UploadedSoundDetailsMapper());
		this.soundFile = soundFile;
		this.description = description;
		this.license = license;
		this.tags = tags;
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();

		queryParams.put(SOUND_FILE_PARAMETER_NAME, soundFile);

		if (name != null) {
			queryParams.put("name", name);
		}

		if (description != null) {
			queryParams.put(DESCRIPTION_PARAMETER_NAME, description);
		}

		if (license != null) {
			queryParams.put(LICENSE_PARAMETER_NAME, license);
		}

		if ((tags != null) && !tags.isEmpty()) {
			final StringBuilder tagsString = new StringBuilder();
			for (final String tag : tags) {
				tagsString.append(tag.replaceAll("\\s", "-"));
				tagsString.append(' ');
			}

			queryParams.put(TAGS_PARAMETER_NAME, tagsString.toString().trim());
		}

		if (pack != null) {
			queryParams.put(PACK_PARAMETER_NAME, pack);
		}

		if (geotag != null) {
			queryParams.put(GEOTAG_PARAMETER_NAME, geotag);
		}

		return queryParams;
	}

}
