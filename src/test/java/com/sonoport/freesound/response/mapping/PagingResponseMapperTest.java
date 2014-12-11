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

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

/**
 * Abstract test class used to check the common functionality associated with subclasses of
 * {@link PagingResponseMapper}.
 *
 * @param <I> Type of element returned in paging response
 */
public abstract class PagingResponseMapperTest<I extends Object> extends MapperTest {

	/** Instance of mapper being tested. */
	private final PagingResponseMapper<I> mapper;

	/** Name of JSON file to use in tests. */
	private final String exampleJSONFilename;

	/** Number of results specified in test JSON file. */
	private final int resultsCount;

	/** Next page URI specified in test JSON file. */
	private final String nextPageURI;

	/** Previous page URI specified in test JSON file. */
	private final String previousPageURI;

	/**
	 * @param mapper Instance of mapper to test
	 * @param exampleJSONFilename Filename of test data
	 * @param resultsCount Number of items in results
	 * @param nextPageURI URI of next page
	 * @param previousPageURI URI of previous page
	 */
	protected PagingResponseMapperTest(
			final PagingResponseMapper<I> mapper,
			final String exampleJSONFilename,
			final int resultsCount,
			final String nextPageURI,
			final String previousPageURI) {
		this.mapper = mapper;
		this.exampleJSONFilename = exampleJSONFilename;
		this.resultsCount = resultsCount;
		this.nextPageURI = nextPageURI;
		this.previousPageURI = previousPageURI;
	}

	/**
	 * Ensure that elements common to paged results are correctly populated.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void testPagingResponseMapper() throws Exception {
		final JSONObject jsonObject = readJSONFile(exampleJSONFilename);

		assertEquals(Integer.valueOf(resultsCount), mapper.extractCount(jsonObject));
		assertEquals(nextPageURI, mapper.extractNextPageURI(jsonObject));
		assertEquals(previousPageURI, mapper.extractPreviousPageURI(jsonObject));

		checkMappedResults(mapper.map(jsonObject));
	}

	/**
	 * Ensure that result items in test data have been correctly mapped.
	 *
	 * @param results The mapped results
	 */
	protected abstract void checkMappedResults(List<I> results);

}
