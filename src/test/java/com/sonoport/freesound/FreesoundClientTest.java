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
package com.sonoport.freesound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.query.Query;
import com.sonoport.freesound.response.Sound;
import com.sonoport.freesound.response.mapping.SoundMapper;

/**
 * Unit tests to ensure the correct operation of {@link FreesoundClient}.
 */
public class FreesoundClientTest {

	/** Client identifier to use when constructing the instance of {@link FreesoundClient}. */
	private static final String CLIENT_ID = "client-id";

	/** Client secret to use when constructing the instance of {@link FreesoundClient}. */
	private static final String CLIENT_SECRET = "foobarbaz";

	/** The name of a route parameter to use in tests. */
	private static final String ROUTE_ELEMENT = "routeElement";

	/** Value of the route parameter. */
	private static final String ROUTE_ELEMENT_VALUE = "1234";

	/** Name of a query parameter to use in tests. */
	private static final String QUERY_PARAMETER = "foo";

	/** Value of query parameter. */
	private static final Object QUERY_PARAMETER_VALUE = "bar";

	/** Path to use in queries for tests. */
	private static final String TEST_PATH = String.format("/test/{%s}", ROUTE_ELEMENT);

	/** Instance of {@link FreesoundClient} to use in unit tests. */
	private FreesoundClient freesoundClient;

	/**
	 * Configure the instance of {@link FreesoundClient} with its dependencies.
	 */
	@Before
	public void configureClient() {
		freesoundClient = new FreesoundClient(CLIENT_ID, CLIENT_SECRET);
	}

	/**
	 * Ensure calls to {@link FreesoundClient#shutdown()} correctly close down all background processes. This is
	 * primarily aimed at ensuring that {@link Unirest#shutdown()} is called.
	 *
	 * @param mockUnirest Mocking of {@link Unirest#shutdown()} method
	 * @throws Exception Any exceptions thrown during test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void shutdownClient(@Mocked("shutdown") final Unirest mockUnirest) throws Exception {
		freesoundClient.shutdown();

		new Verifications() {
			{
				mockUnirest.shutdown();
			}
		};
	}

	/**
	 * Ensure that a {@link FreesoundClientException} is thrown should any errors be encountered when calling
	 * {@link FreesoundClient#shutdown()} is called.
	 *
	 * @param mockUnirest Mocking of {@link Unirest#shutdown()} method
	 * @throws Exception Any exceptions thrown during test
	 */
	@SuppressWarnings("static-access")
	@Test (expected = FreesoundClientException.class)
	public void unirestShutdownFails(@Mocked("shutdown") final Unirest mockUnirest) throws Exception {
		new Expectations() {
			{
				mockUnirest.shutdown(); result = new IOException();
			}
		};

		freesoundClient.shutdown();
	}

	/**
	 * Test the {@link FreesoundClient#executeQuery(Query)} method, to ensure it correctly constructs and submits an
	 * HTTP request, and processes the results.
	 *
	 * @param mockUnirest Mock version of the {@link Unirest} library
	 * @param mockGetRequest Mock {@link GetRequest}
	 * @param mockHttpResponse Mock {@link HttpResponse}
	 * @param mockResultsMapper Mock {@link SoundMapper}
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void executeQuery(
			@Mocked final Unirest mockUnirest,
			@Mocked final GetRequest mockGetRequest,
			@Mocked final HttpResponse<JsonNode> mockHttpResponse,
			@Mocked final SoundMapper mockResultsMapper) throws Exception {
		final Sound sound = new Sound();
		new Expectations() {
			{
				mockUnirest.get(FreesoundClient.API_ENDPOINT + TEST_PATH); result = mockGetRequest;

				mockGetRequest.header("Authorization", "Token " + CLIENT_SECRET);
				mockGetRequest.routeParam(ROUTE_ELEMENT, ROUTE_ELEMENT_VALUE);
				mockGetRequest.fields(with(new Delegate<HashMap<String, Object>>() {
					@SuppressWarnings("unused")
					void checkRequestParameters(final Map<String, Object> queryParameters) {
						assertNotNull(queryParameters);
						assertTrue(queryParameters.size() == 1);
						assertEquals(QUERY_PARAMETER_VALUE, queryParameters.get(QUERY_PARAMETER));
					}
				}));

				mockGetRequest.asJson(); result = mockHttpResponse;
				mockHttpResponse.getCode(); result = 200;
				mockResultsMapper.map(mockHttpResponse.getBody().getObject()); result = sound;
			}
		};

		final Query<Sound> query = new TestQuery(mockResultsMapper);
		freesoundClient.executeQuery(query);

		assertSame(sound, query.getResults());
	}

	/**
	 * Ensure the correct exception is thrown when attempting to use {@link FreesoundClient#nextPage(PagingQuery)} on a
	 * query that has no more pages to return.
	 *
	 * @param pagingQuery The {@link PagingQuery} to attempt to retrieve the next page for
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test (expected = FreesoundClientException.class)
	public void noNextPageInPagingQuery(@Mocked final PagingQuery<?, ?> pagingQuery) throws Exception {
		new Expectations() {
			{
				pagingQuery.hasNextPage(); result = false;
			}
		};

		freesoundClient.nextPage(pagingQuery);
	}

	/**
	 * Ensure the correct exception is thrown when attempting to use {@link FreesoundClient#previousPage(PagingQuery)}
	 * when there is no previous page to return.
	 *
	 * @param pagingQuery The {@link PagingQuery} to attempt to retrieve the previous page for
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test (expected = FreesoundClientException.class)
	public void noPreviousPageInPagingQuery(@Mocked final PagingQuery<?, ?> pagingQuery) throws Exception {
		new Expectations() {
			{
				pagingQuery.hasPreviousPage(); result = false;
			}
		};

		freesoundClient.previousPage(pagingQuery);
	}

	/**
	 * Simple {@link Query} class for usng in tests.
	 */
	private class TestQuery extends Query<Sound> {

		/**
		 * @param resultsMapper Mapper to use
		 */
		protected TestQuery(final SoundMapper resultsMapper) {
			super(HTTPRequestMethod.GET, TEST_PATH, resultsMapper);
		}

		@Override
		public Map<String, String> getRouteParameters() {
			final Map<String, String> routeParameters = new HashMap<>();
			routeParameters.put(ROUTE_ELEMENT, ROUTE_ELEMENT_VALUE);

			return routeParameters;
		}

		@Override
		public Map<String, Object> getQueryParameters() {
			final Map<String, Object> queryParameters = new HashMap<>();
			queryParameters.put(QUERY_PARAMETER, QUERY_PARAMETER_VALUE);
			return queryParameters;
		}

	}
}
