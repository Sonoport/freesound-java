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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.sonoport.freesound.query.BinaryResponseQuery;
import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.PagingQuery;
import com.sonoport.freesound.query.oauth2.AccessTokenQuery;
import com.sonoport.freesound.query.oauth2.OAuth2AccessTokenRequest;
import com.sonoport.freesound.query.oauth2.RefreshOAuth2AccessTokenRequest;
import com.sonoport.freesound.response.AccessTokenDetails;
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

	/** OAuth2 authorisation code. */
	private static final String OAUTH_AUTHORISATION_CODE = "abc123";

	/** OAuth2 bearer token. */
	private static final String OAUTH_ACCESS_TOKEN = "def456";

	/** OAuth2 refresh token. */
	private static final String OAUTH_REFRESH_TOKEN = "ghi789";

	/** OAuth2 access scope. */
	private static final String OAUTH_SCOPE = "read write";

	/** OAuth2 bearer token expiry. */
	private static final int OAUTH_TOKEN_EXPIRES_IN = 84600;

	/** JSON representation of response from OAuth2 token endpoint. */
	private static final JSONObject OAUTH_TOKEN_DETAILS_JSON =
			new JSONObject(
					String.format(
						"{ \"access_token\":\"%s\", \"scope\":\"%s\", \"expires_in\":%d, \"refresh_token\":\"%s\" }",
						OAUTH_ACCESS_TOKEN, OAUTH_SCOPE, OAUTH_TOKEN_EXPIRES_IN, OAUTH_REFRESH_TOKEN));

	/** Custom User-Agent string to use in tests. */
	private static final String USER_AGENT_STRING = "freesound-java/test";

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
	 * Ensure that instances of {@link FreesoundClient} are created correctly with default options set.
	 *
	 * @param mockUnirest Mock {@link Unirest} object
	 */
	@SuppressWarnings("static-access")
	@Test
	public void defaultHeadersSetCorrectly(@Mocked final Unirest mockUnirest) {
		new FreesoundClient(CLIENT_ID, CLIENT_SECRET);

		new Verifications() {
			{
				mockUnirest.setDefaultHeader(
						FreesoundClient.HTTP_ACCEPT_HEADER, FreesoundClient.CONTENT_TYPES_TO_ACCEPT);
				mockUnirest.setDefaultHeader(
						FreesoundClient.HTTP_USER_AGENT_HEADER, FreesoundClient.DEFAULT_USER_AGENT_STRING);
			}
		};
	}

	/**
	 * Ensure that instances of {@link FreesoundClient} are created correctly with a custom User-Agent string specified.
	 *
	 * @param mockUnirest Mock {@link Unirest} object
	 */
	@SuppressWarnings("static-access")
	@Test
	public void headersSetCorrectlyWithCustomUserAgent(@Mocked final Unirest mockUnirest) {
		new FreesoundClient(CLIENT_ID, CLIENT_SECRET, USER_AGENT_STRING);

		new Verifications() {
			{
				mockUnirest.setDefaultHeader(
						FreesoundClient.HTTP_ACCEPT_HEADER, FreesoundClient.CONTENT_TYPES_TO_ACCEPT);
				mockUnirest.setDefaultHeader(FreesoundClient.HTTP_USER_AGENT_HEADER, USER_AGENT_STRING);
			}
		};
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
	 * HTTP GET request, and processes the results.
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
	public void executeGetQuery(
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

		final JSONResponseQuery<Sound> query = new TestJSONResponseQuery(HTTPRequestMethod.GET, mockResultsMapper);
		freesoundClient.executeQuery(query);

		assertSame(sound, query.getResults());
	}

	/**
	 * Test the {@link FreesoundClient#executeQuery(Query)} method, to ensure it correctly constructs and submits an
	 * HTTP POST request, and processes the results.
	 *
	 * @param mockUnirest Mock version of the {@link Unirest} library
	 * @param mockPostRequest Mock {@link HttpRequestWithBody} representing a POST call
	 * @param mockHttpResponse Mock {@link HttpResponse}
	 * @param mockResultsMapper Mock {@link SoundMapper}
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void executePostQuery(
			@Mocked final Unirest mockUnirest,
			@Mocked final HttpRequestWithBody mockPostRequest,
			@Mocked final HttpResponse<JsonNode> mockHttpResponse,
			@Mocked final SoundMapper mockResultsMapper) throws Exception {
		final Sound sound = new Sound();
		new Expectations() {
			{
				mockUnirest.post(FreesoundClient.API_ENDPOINT + TEST_PATH); result = mockPostRequest;

				mockPostRequest.header("Authorization", "Token " + CLIENT_SECRET);
				mockPostRequest.routeParam(ROUTE_ELEMENT, ROUTE_ELEMENT_VALUE);
				mockPostRequest.fields(with(new Delegate<HashMap<String, Object>>() {
					@SuppressWarnings("unused")
					void checkRequestParameters(final Map<String, Object> queryParameters) {
						assertNotNull(queryParameters);
						assertTrue(queryParameters.size() == 1);
						assertEquals(QUERY_PARAMETER_VALUE, queryParameters.get(QUERY_PARAMETER));
					}
				}));

				mockPostRequest.asJson(); result = mockHttpResponse;
				mockHttpResponse.getCode(); result = 200;
				mockResultsMapper.map(mockHttpResponse.getBody().getObject()); result = sound;
			}
		};

		final JSONResponseQuery<Sound> query = new TestJSONResponseQuery(HTTPRequestMethod.POST, mockResultsMapper);
		freesoundClient.executeQuery(query);

		assertSame(sound, query.getResults());
	}

	/**
	 * Test the {@link FreesoundClient#executeQuery(Query)} method, to ensure it correctly constructs and submits an
	 * HTTP GET request, and processes the results.
	 *
	 * @param mockUnirest Mock version of the {@link Unirest} library
	 * @param mockGetRequest Mock {@link GetRequest}
	 * @param mockHttpResponse Mock {@link HttpResponse}
	 * @param mockInputStream Mock {@link InputStream} response
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void executeBinaryResponseQuery(
			@Mocked final Unirest mockUnirest,
			@Mocked final GetRequest mockGetRequest,
			@Mocked final HttpResponse<InputStream> mockHttpResponse,
			@Mocked final InputStream mockInputStream) throws Exception {
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

				mockGetRequest.asBinary(); result = mockHttpResponse;
				mockHttpResponse.getCode(); result = 200;
				mockHttpResponse.getBody(); result = mockInputStream;
			}
		};

		final TestBinaryResponseQuery query = new TestBinaryResponseQuery();
		freesoundClient.executeQuery(query);

		assertSame(mockInputStream, query.getResults());
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
	 * Test that requests to redeem an authorisation code for an OAuth2 bearer token are correctly constructed and
	 * passed to the appropriate endpoint.
	 *
	 * @param mockUnirest Mock version of the {@link Unirest} library
	 * @param mockTokenRequest Mock {@link HttpRequestWithBody} representing a POST call to token endpoint
	 * @param mockHttpResponse Mock {@link HttpResponse}
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void requestAccessToken(
			@Mocked final Unirest mockUnirest,
			@Mocked final HttpRequestWithBody mockTokenRequest,
			@Mocked final HttpResponse<JsonNode> mockHttpResponse) throws Exception {
		new Expectations() {
			{
				mockUnirest.post(FreesoundClient.API_ENDPOINT + AccessTokenQuery.OAUTH_TOKEN_ENDPOINT_PATH);
				result = mockTokenRequest;

				mockTokenRequest.fields(with(new Delegate<HashMap<String, Object>>() {
					@SuppressWarnings("unused")
					void checkRequestParameters(final Map<String, Object> queryParameters) {
						assertEquals(CLIENT_ID, queryParameters.get(AccessTokenQuery.CLIENT_ID_PARAMETER_NAME));
						assertEquals(CLIENT_SECRET, queryParameters.get(AccessTokenQuery.CLIENT_SECRET_PARAMETER_NAME));
						assertEquals(
								OAuth2AccessTokenRequest.GRANT_TYPE,
								queryParameters.get(AccessTokenQuery.GRANT_TYPE_PARAMETER_NAME));
						assertEquals(
								OAUTH_AUTHORISATION_CODE,
								queryParameters.get(OAuth2AccessTokenRequest.CODE_PARAMETER_NAME));
					}
				}));

				mockTokenRequest.asJson(); result = mockHttpResponse;
				mockHttpResponse.getCode(); result = 200;
				mockHttpResponse.getBody().getObject(); result = OAUTH_TOKEN_DETAILS_JSON;
			}
		};

		final AccessTokenDetails accessTokenDetails =
				freesoundClient.redeemAuthorisationCodeForAccessToken(OAUTH_AUTHORISATION_CODE);

		assertEquals(OAUTH_ACCESS_TOKEN, accessTokenDetails.getAccessToken());
		assertEquals(OAUTH_REFRESH_TOKEN, accessTokenDetails.getRefreshToken());
		assertEquals(OAUTH_SCOPE, accessTokenDetails.getScope());
		assertEquals(OAUTH_TOKEN_EXPIRES_IN, accessTokenDetails.getExpiresIn());
	}

	/**
	 * Test that requests to renew an OAuth2 bearer token are correctly constructed and passed to the appropriate
	 * endpoint.
	 *
	 * @param mockUnirest Mock version of the {@link Unirest} library
	 * @param mockTokenRequest Mock {@link HttpRequestWithBody} representing a POST call to token endpoint
	 * @param mockHttpResponse Mock {@link HttpResponse}
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void refreshAccessToken(
			@Mocked final Unirest mockUnirest,
			@Mocked final HttpRequestWithBody mockTokenRequest,
			@Mocked final HttpResponse<JsonNode> mockHttpResponse) throws Exception {
		new Expectations() {
			{
				mockUnirest.post(FreesoundClient.API_ENDPOINT + AccessTokenQuery.OAUTH_TOKEN_ENDPOINT_PATH);
				result = mockTokenRequest;

				mockTokenRequest.fields(with(new Delegate<HashMap<String, Object>>() {
					@SuppressWarnings("unused")
					void checkRequestParameters(final Map<String, Object> queryParameters) {
						assertEquals(CLIENT_ID, queryParameters.get(AccessTokenQuery.CLIENT_ID_PARAMETER_NAME));
						assertEquals(CLIENT_SECRET, queryParameters.get(AccessTokenQuery.CLIENT_SECRET_PARAMETER_NAME));
						assertEquals(
								RefreshOAuth2AccessTokenRequest.GRANT_TYPE,
								queryParameters.get(AccessTokenQuery.GRANT_TYPE_PARAMETER_NAME));
						assertEquals(
								OAUTH_REFRESH_TOKEN,
								queryParameters.get(RefreshOAuth2AccessTokenRequest.CODE_PARAMETER_NAME));
					}
				}));

				mockTokenRequest.asJson(); result = mockHttpResponse;
				mockHttpResponse.getCode(); result = 200;
				mockHttpResponse.getBody().getObject(); result = OAUTH_TOKEN_DETAILS_JSON;
			}
		};

		final AccessTokenDetails accessTokenDetails = freesoundClient.refreshAccessToken(OAUTH_REFRESH_TOKEN);

		assertEquals(OAUTH_ACCESS_TOKEN, accessTokenDetails.getAccessToken());
		assertEquals(OAUTH_REFRESH_TOKEN, accessTokenDetails.getRefreshToken());
		assertEquals(OAUTH_SCOPE, accessTokenDetails.getScope());
		assertEquals(OAUTH_TOKEN_EXPIRES_IN, accessTokenDetails.getExpiresIn());
	}

	/**
	 * Simple {@link JSONResponseQuery} subclass for using in tests.
	 */
	private class TestJSONResponseQuery extends JSONResponseQuery<Sound> {

		/**
		 * @param httpRequestMethod The HTTP Request method to use
		 * @param resultsMapper Mapper to use
		 */
		protected TestJSONResponseQuery(final HTTPRequestMethod httpRequestMethod, final SoundMapper resultsMapper) {
			super(httpRequestMethod, TEST_PATH, resultsMapper);
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

	/**
	 * Simple subclass of {@link BinaryResponseQuery} to use in tests.
	 */
	private class TestBinaryResponseQuery extends BinaryResponseQuery {

		/**
		 * No-arg constructor.
		 */
		protected TestBinaryResponseQuery() {
			super(HTTPRequestMethod.GET, TEST_PATH);
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
