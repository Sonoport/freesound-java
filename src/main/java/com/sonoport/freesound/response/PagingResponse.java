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
 * Class used to represent a list of items returned by the API that may spread over a number of pages.
 *
 * @param <I> The type of the items in the results
 */
public class PagingResponse<I extends Object> extends Response<List<I>> {

	/** The number of sounds matching the search terms. */
	private int count;

	/** The URI to the next page of results (if any). */
	private String nextPageURI;

	/** The URI to the previous page of results (if any). */
	private String previousPageURI;

	/**
	 * @param responseStatus HTTP response status
	 * @param responseStatusString HTTP response status string
	 */
	public PagingResponse(final int responseStatus, final String responseStatusString) {
		super(responseStatus, responseStatusString);
	}

	/**
	 * @return Whether there are further pages of results for the associated query
	 */
	public boolean hasNextPage() {
		return nextPageURI != null;
	}

	/**
	 * @return Whether there are previous pages of results for the associated query
	 */
	public boolean hasPreviousPage() {
		return previousPageURI != null;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the nextPageURI
	 */
	public String getNextPageURI() {
		return nextPageURI;
	}

	/**
	 * @return the previousPageURI
	 */
	public String getPreviousPageURI() {
		return previousPageURI;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(final int count) {
		this.count = count;
	}

	/**
	 * @param nextPageURI the nextPageURI to set
	 */
	public void setNextPageURI(final String nextPageURI) {
		this.nextPageURI = nextPageURI;
	}

	/**
	 * @param previousPageURI the previousPageURI to set
	 */
	public void setPreviousPageURI(final String previousPageURI) {
		this.previousPageURI = previousPageURI;
	}

}
