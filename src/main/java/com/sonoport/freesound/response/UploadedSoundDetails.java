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
package com.sonoport.freesound.response;

/**
 * Details returned after uploading a sound to freesound.org. The fields populated will depend on the options set when
 * uploading. See details at http://www.freesound.org/docs/api/resources_apiv2.html#upload-sound-oauth2-required.
 */
public class UploadedSoundDetails {

	/** Detail message received. */
	private String detail;

	/** Id of the uploaded sound. */
	private Integer id;

	/** Filename of the uploaded sound. */
	private String filename;

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(final String detail) {
		this.detail = detail;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(final String filename) {
		this.filename = filename;
	}
}
