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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of the {@link MeQuery} class.
 */
public class MeQueryTest {

	/** OAuth bearer token to use in tests. */
	private static final String OAUTH_TOKEN = "abc123def456";

	/**
	 * Ensure that {@link MeQuery} instances are correctly constructed.
	 */
	@Test
	public void meQueryCorrectlyConstructed() {
		final MeQuery meQuery = new MeQuery(OAUTH_TOKEN);

		assertEquals(OAUTH_TOKEN, meQuery.getOauthToken());
		assertTrue(meQuery.getRouteParameters().isEmpty());
		assertTrue(meQuery.getQueryParameters().isEmpty());
	}
}
