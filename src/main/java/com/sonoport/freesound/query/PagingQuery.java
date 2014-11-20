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
package com.sonoport.freesound.query;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.sonoport.freesound.response.mapping.Mapper;

/**
 * Extension of {@link Query} that represents API calls that can return results that span multiple pages. The main
 * example of this are search queries, where there may be too many results to return in one go.
 *
 * @param <Q> The type of the {@link PagingQuery} (required to implement Fluent API elements)
 * @param <R> The DTO type to return results as
 */
public abstract class PagingQuery<Q extends PagingQuery<Q, R>, R extends Object> extends Query<R> {

	/** The default page size if none is specified. */
	private static final int DEFAULT_PAGE_SIZE = 15;

	/** The maximum size of a single page. 150 is the specified maximum in the API documentation. */
	private static final int MAXIMUM_PAGE_SIZE = 150;

	/** The page that will be requested in the query. */
	private int page;

	/** The number of results to return per page. */
	private int pageSize;

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path The URI path to the API endpoint
	 * @param resultsMapper {@link Mapper} to convert results
	 */
	protected PagingQuery(
			final HTTPRequestMethod httpRequestMethod, final String path, final Mapper<JSONObject, R> resultsMapper) {
		this(httpRequestMethod, path, resultsMapper, DEFAULT_PAGE_SIZE);
	}

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path The URI path to the API endpoint
	 * @param resultsMapper {@link Mapper} to convert results
	 * @param pageSize The number of results per page
	 */
	protected PagingQuery(
			final HTTPRequestMethod httpRequestMethod,
			final String path,
			final Mapper<JSONObject, R> resultsMapper,
			final int pageSize) {
		this(httpRequestMethod, path, resultsMapper, pageSize, 1);
	}

	/**
	 * @param httpRequestMethod HTTP method to use for query
	 * @param path The URI path to the API endpoint
	 * @param resultsMapper {@link Mapper} to convert results
	 * @param pageSize The number of results per page
	 * @param startPage The page to start at
	 */
	protected PagingQuery(
			final HTTPRequestMethod httpRequestMethod,
			final String path,
			final Mapper<JSONObject, R> resultsMapper,
			final int pageSize,
			final int startPage) {
		super(httpRequestMethod, path, resultsMapper);
		setPageSize(pageSize);
		setPage(startPage);
	}

	/**
	 * Set the page of results to retrieve in the query, using a Fluent API style.
	 *
	 * @param pageNumber The page number to retrieve
	 * @return The current query
	 */
	@SuppressWarnings("unchecked")
	public Q page(final int pageNumber) {
		setPage(pageNumber);
		return (Q) this;
	}

	/**
	 * Set the number of results to return per page, using a Fluent API style.
	 *
	 * @param pageSize The number of results per page
	 * @return The current query
	 */
	@SuppressWarnings("unchecked")
	public Q pageSize(final int pageSize) {
		setPageSize(pageSize);
		return (Q) this;
	}

	@Override
	public final Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("page", Integer.valueOf(page));
		queryParams.put("page_size", Integer.valueOf(pageSize));

		queryParams.putAll(getRequestParameters());

		return queryParams;
	}

	/**
	 * Build the set of request parameters specific to the implementing query class.
	 *
	 * @return Parameters relating to the query
	 */
	protected abstract Map<String, Object> getRequestParameters();

	/**
	 * @return Whether there is another page of results after the current one
	 */
	public abstract boolean hasNextPage();

	/**
	 * @return Whether there is page of results before the current one
	 */
	public abstract boolean hasPreviousPage();

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(final int page) {
		this.page = page;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(final int pageSize) {
		if (pageSize <= MAXIMUM_PAGE_SIZE) {
			this.pageSize = pageSize;
		} else {
			this.pageSize = MAXIMUM_PAGE_SIZE;
		}
	}

}
