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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.sonoport.freesound.query.JSONResponseQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link SoundInstanceQuery}.
 */
public class SoundInstanceQueryTest extends JSONResponseQueryTest<SoundInstanceQuery> {

	/** The sound identifier to use in tests. */
	private static final int SOUND_ID = 123;

	/**
	 * Ensure that collections of parameters are correctly constructed.
	 */
	@Test
	public void queryCorrectlyCreated() {
		final SoundInstanceQuery query = new SoundInstanceQuery(SOUND_ID);

		final Map<String, String> routeParameters = query.getRouteParameters();
		assertTrue(routeParameters.size() == 1);
		assertEquals(String.valueOf(SOUND_ID), routeParameters.get(SoundInstanceQuery.SOUND_IDENTIFIER_PARAMETER));

		final Map<String, Object> queryParameters = query.getQueryParameters();
		assertNotNull(queryParameters);
		assertTrue(queryParameters.isEmpty());
	}

	@Override
	protected SoundInstanceQuery newQueryInstance() {
		return new SoundInstanceQuery(SOUND_ID);
	}

}
