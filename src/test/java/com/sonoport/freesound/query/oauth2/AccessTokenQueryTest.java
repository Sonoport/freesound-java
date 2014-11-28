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
package com.sonoport.freesound.query.oauth2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the common code within {@link AccessTokenQuery} is operating correctly. Tests for subclasses of
 * {@link AccessTokenQuery} should extend this class, so that these common tests are run against the subclasses.
 *
 * @param <T> The subclass of {@link AccessTokenQuery} under test
 */
public abstract class AccessTokenQueryTest<T extends AccessTokenQuery> extends JSONResponseQueryTest<T> {

	/** Client Id used in tests. */
	private final String clientId;

	/** Client Secret used in tests. */
	private final String clientSecret;

	/** Grant type requested by subclass. */
	private final String grantType;

	/** Name of the parameter the subclass passes its code on under. */
	private final String codeParameterName;

	/** The code passed on by the subclass. */
	private final String code;

	/**
	 * @param clientId Client Id used in tests
	 * @param clientSecret Client Secret used in tests
	 * @param grantType Grant type requested by subclass
	 * @param codeParameterName Name of the parameter the subclass passes its code on under
	 * @param code Code passed on by the subclass
	 */
	protected AccessTokenQueryTest(
			final String clientId,
			final String clientSecret,
			final String grantType,
			final String codeParameterName,
			final String code) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.grantType = grantType;
		this.codeParameterName = codeParameterName;
		this.code = code;
	}

	/**
	 * Ensure that the parameters required by the OAuth2 token endpoint have all been set by the subclass.
	 */
	@Test
	public void checkOAuthQueryParametersCorrectlySet() {
		final T tokenQuery = newQueryInstance();

		final Map<String, Object> queryParameters = tokenQuery.getQueryParameters();

		assertEquals(clientId, queryParameters.get(AccessTokenQuery.CLIENT_ID_PARAMETER_NAME));
		assertEquals(clientSecret, queryParameters.get(AccessTokenQuery.CLIENT_SECRET_PARAMETER_NAME));
		assertEquals(grantType, queryParameters.get(AccessTokenQuery.GRANT_TYPE_PARAMETER_NAME));
		assertEquals(code, queryParameters.get(codeParameterName));
	}

	/**
	 * Ensure that no route parameters have been specified by subclasses.
	 */
	@Test
	public void checkRouteParameters() {
		final T tokenQuery = newQueryInstance();

		assertTrue(tokenQuery.getRouteParameters().isEmpty());
	}
}
