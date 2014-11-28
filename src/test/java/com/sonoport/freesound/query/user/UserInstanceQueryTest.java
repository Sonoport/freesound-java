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
package com.sonoport.freesound.query.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure {@link UserInstanceQuery} objects are correctly created.
 */
public class UserInstanceQueryTest extends JSONResponseQueryTest<UserInstanceQuery> {

	/** User name to use in tests. */
	private static final String USERNAME = "foobar";

	/**
	 * Ensure query is correctly created under normal circumstances.
	 */
	@Test
	public void queryCorrectlyCreated() {
		final UserInstanceQuery query = new UserInstanceQuery(USERNAME);

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(USERNAME, query.getRouteParameters().get(UserInstanceQuery.USERNAME_PARAMETER));

		assertNotNull(query.getQueryParameters());
		assertTrue(query.getQueryParameters().isEmpty());
	}

	/**
	 * Ensure that the correct exception is thrown if an empty string is passed for the username.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test (expected = IllegalArgumentException.class)
	public void emptyStringForUsername() throws Exception {
		new UserInstanceQuery("");
	}

	/**
	 * Ensure that the correct exception is thrown if only whitespace is passed for the username.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test (expected = IllegalArgumentException.class)
	public void whitespaceForUsername() throws Exception {
		new UserInstanceQuery("  \t  ");
	}

	/**
	 * Ensure that the correct exception is thrown if a null value is passed for the username.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test (expected = IllegalArgumentException.class)
	public void nullUsername() throws Exception {
		new UserInstanceQuery(null);
	}

	@Override
	protected UserInstanceQuery newQueryInstance() {
		return new UserInstanceQuery(USERNAME);
	}
}
