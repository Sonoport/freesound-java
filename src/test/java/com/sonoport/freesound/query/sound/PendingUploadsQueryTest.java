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
package com.sonoport.freesound.query.sound;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link PendingUploadsQuery}.
 */
public class PendingUploadsQueryTest extends JSONResponseQueryTest<PendingUploadsQuery> {

	/** OAuth token to use in tests. */
	private static final String OAUTH_TOKEN = "abc123def";

	/**
	 * Ensure that insatnces of {@link PendingUploadsQuery} are correctly constructed.
	 */
	@Test
	public void queryCorrectlyConstructed() {
		final PendingUploadsQuery query = newQueryInstance();

		assertTrue(query.getRouteParameters().isEmpty());
		assertTrue(query.getQueryParameters().isEmpty());
		assertEquals(OAUTH_TOKEN, query.getOauthToken());
	}

	@Override
	protected PendingUploadsQuery newQueryInstance() {
		return new PendingUploadsQuery(OAUTH_TOKEN);
	}

}
