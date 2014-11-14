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

import java.util.List;

/**
 * Class representing the contents of a search performed against the freesound.org API.
 */
public class SoundResultsList {

	/** The number of sounds matching the search terms. */
	private int count;

	/** The current page of search results. */
	private List<Sound> sounds;

	/** The URI to the next page of results (if any). */
	private String nextPageURI;

	/** The URI to the previous page of results (if any). */
	private String previousPageURI;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(final int count) {
		this.count = count;
	}

	/**
	 * @return the sounds
	 */
	public List<Sound> getSounds() {
		return sounds;
	}

	/**
	 * @param sounds the sounds to set
	 */
	public void setSounds(final List<Sound> sounds) {
		this.sounds = sounds;
	}

	/**
	 * @return the nextPageURI
	 */
	public String getNextPageURI() {
		return nextPageURI;
	}

	/**
	 * @param nextPageURI the nextPageURI to set
	 */
	public void setNextPageURI(final String nextPageURI) {
		this.nextPageURI = nextPageURI;
	}

	/**
	 * @return the previousPageURI
	 */
	public String getPreviousPageURI() {
		return previousPageURI;
	}

	/**
	 * @param previousPageURI the previousPageURI to set
	 */
	public void setPreviousPageURI(final String previousPageURI) {
		this.previousPageURI = previousPageURI;
	}
}
