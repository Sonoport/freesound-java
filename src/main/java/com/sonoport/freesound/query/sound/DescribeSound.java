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
import java.util.Set;

import com.sonoport.freesound.License;
import com.sonoport.freesound.response.UploadedSoundDetails;
import com.sonoport.freesound.response.mapping.UploadedSoundDetailsMapper;

/**
 * Query used to provide additional details relating to a previously uploaded sound. Sounds requiring these queries are
 * listed as 'Pending Description' in the 'Pending Uploads' query.
 *
 * Note that there is a bug in the API meaning that if the optional parameters (name and pack) are not specified, the
 * call will fail (with a 500 response). A bug has been raised with Freesound at:
 * https://github.com/MTG/freesound/issues/648
 */
public class DescribeSound extends AbstractSoundUploadQuery<UploadedSoundDetails, DescribeSound> {

	/** Name of the parameter to pass the filename through as. */
	protected static final String UPLOAD_FILENAME_PARAMETER_NAME = "upload_filename";

	/** Path to API endpoint. */
	private static final String PATH = "/sounds/describe/";

	/** Name of the previously uploaded file. */
	private final String uploadFilename;

	/**
	 * @param uploadFilename Name of the previously uploaded file
	 * @param description Description to apply to sound
	 * @param license License to apply to sound
	 * @param tags Tags to associate with sound
	 * @param oauthToken OAuth2 access token to present
	 */
	public DescribeSound(
			final String uploadFilename,
			final String description,
			final License license,
			final Set<String> tags,
			final String oauthToken) {
		super(PATH, oauthToken, new UploadedSoundDetailsMapper());

		this.uploadFilename = uploadFilename;
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
		final Map<String, Object> queryParameters = super.getQueryParameters();

		queryParameters.put(UPLOAD_FILENAME_PARAMETER_NAME, uploadFilename);

		return queryParameters;
	}

}
