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
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.License;

/**
 * Query used to upload a sound file to Freesound.
 *
 * API documentation: http://www.freesound.org/docs/api/resources_apiv2.html#upload-sound-oauth2-required
 */
public class UploadSound extends AbstractSoundUploadQuery<UploadSound> {

	/** Parameter to pass the sound file through as. */
	protected static final String SOUND_FILE_PARAMETER_NAME = "audiofile";

	/** Path to API endpoint. */
	private static final String PATH = "/sounds/upload/";

	/** The file to upload. */
	private final File soundFile;

	/**
	 * Simplest means of uploading a sound - will have default details associated with it. Further details can be added
	 * using the Fluent API methods, or at a later time by using the Describe Sound query type.
	 *
	 * @param soundFile The sound file to upload
	 * @param oauthToken OAuth2 credential
	 */
	public UploadSound(final File soundFile, final String oauthToken) {
		this(soundFile, null, null, null, oauthToken);
	}

	/**
	 * More complete means of uploading sound. The three additional fields are mandatory together when one is specified.
	 * Further details can be added using the Fluent API methods.
	 *
	 * @param soundFile The sound file to upload
	 * @param description Description of the sound
	 * @param license License attached to sound
	 * @param tags Tags associated with sound
	 * @param oauthToken OAuth2 credential
	 */
	public UploadSound(
			final File soundFile,
			final String description,
			final License license,
			final Set<String> tags,
			final String oauthToken) {
		super(PATH, oauthToken);

		this.soundFile = soundFile;

		this.setDescription(description);
		this.setLicense(license);
		this.setTags(tags);
	}

	@Override
	public Map<String, String> getRouteParameters() {
		return Collections.emptyMap();
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = super.getQueryParameters();

		queryParams.put(SOUND_FILE_PARAMETER_NAME, soundFile);

		return queryParams;
	}

}
