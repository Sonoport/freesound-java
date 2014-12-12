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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit tests the ensure that common code associated with queries that return paged results operates correctly.
 *
 * @param <R> The data type returned in the list of results
 * @param <T> The subclass of {@link PagingQuery} under test
 */
public abstract class PagingQueryTest<R extends Object, T extends PagingQuery<T, R>> extends JSONResponseQueryTest<T> {

	/**
	 * Ensure that Fluent API methods correctly populate the page/page size values.
	 */
	@Test
	public void fluentAPICorrectlyPopulatesValues() {
		final int pageSize = 50;
		final int page = 2;

		final T query = newQueryInstance();
		query.pageSize(pageSize).page(page);

		assertEquals(pageSize, query.getPageSize());
		assertEquals(page, query.getPage());
	}

	/**
	 * Ensure that we cannot set the page size to a value larger than the maximum specified by the API.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void cannotSetPageSizeLargerThanMaximum() {
		final T query = newQueryInstance();
		query.pageSize(PagingQuery.MAXIMUM_PAGE_SIZE + 1);
	}

	/**
	 * Ensure that we cannot specify a page size less than 1.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void cannotSetPageSizeLessThanOne() {
		final T query = newQueryInstance();
		query.pageSize(0);
	}

	/**
	 * Ensure that we cannot specify a page number less than 1.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void cannotSetPageNumberLessThanOne() {
		final T query = newQueryInstance();
		query.page(0);
	}

	/**
	 * Test that {@link PagingQuery#hasNextPage()} and {@link PagingQuery#hasPreviousPage()} return correct values when
	 * we are at the first page of results.
	 */
//	@Test
//	public void atFirstPageOfResults() {
//		final T query = newQueryInstance();
//		final PagingResponse<R> results = new PagingResponse<>();
//
//		results.setNextPageURI("https://www.freesound.org/sounds?page=2");
//		results.setPreviousPageURI(null);
//
//		query.setResults(results);
//
//		assertFalse(query.hasPreviousPage());
//		assertTrue(query.hasNextPage());
//	}

	/**
	 * Test that {@link PagingQuery#hasNextPage()} and {@link PagingQuery#hasPreviousPage()} return correct values when
	 * we are in the middle of the resultset (i.e. results have previous and next pages).
	 */
//	@Test
//	public void inMiddleOfResults() {
//		final T query = newQueryInstance();
//		final PagingResponse<R> results = new PagingResponse<>();
//
//		results.setNextPageURI("https://www.freesound.org/sounds?page=1");
//		results.setPreviousPageURI("https://www.freesound.org/sounds?page=3");
//
//		query.setResults(results);
//
//		assertTrue(query.hasPreviousPage());
//		assertTrue(query.hasNextPage());
//	}

	/**
	 * Test that {@link PagingQuery#hasNextPage()} and {@link PagingQuery#hasPreviousPage()} return correct values when
	 * we are at the last page of results.
	 */
//	@Test
//	public void atLastPageOfResults() {
//		final T query = newQueryInstance();
//		final PagingResponse<R> results = new PagingResponse<>();
//
//		results.setNextPageURI(null);
//		results.setPreviousPageURI("https://www.freesound.org/sounds?page=1");
//
//		query.setResults(results);
//
//		assertTrue(query.hasPreviousPage());
//		assertFalse(query.hasNextPage());
//	}

}
