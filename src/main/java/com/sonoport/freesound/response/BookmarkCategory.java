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
 * Representation of a single bookmark category belonging to a user.
 */
public class BookmarkCategory {

	/** URI of the bookmark category in Freesound. */
	private String url;

	/** Name that the user has given to the bookmark category. */
	private String name;

	/** Number of sounds under the bookmark category. */
	private int numberOfSounds;

	/** URI to a page with the list of sounds in this bookmark category. */
	private String soundsURL;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the numberOfSounds
	 */
	public int getNumberOfSounds() {
		return numberOfSounds;
	}

	/**
	 * @param numberOfSounds the numberOfSounds to set
	 */
	public void setNumberOfSounds(final int numberOfSounds) {
		this.numberOfSounds = numberOfSounds;
	}

	/**
	 * @return the soundsURL
	 */
	public String getSoundsURL() {
		return soundsURL;
	}

	/**
	 * @param soundsURL the soundsURL to set
	 */
	public void setSoundsURL(final String soundsURL) {
		this.soundsURL = soundsURL;
	}

}
