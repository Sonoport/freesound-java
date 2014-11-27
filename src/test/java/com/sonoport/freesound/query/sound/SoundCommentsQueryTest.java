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

/**
 * Unit tests to ensure the correct operation of {@link SoundCommentsQuery}.
 */
public class SoundCommentsQueryTest {

	/** Sound identifier to use in tests. */
	private static final int SOUND_ID = 1234;

	/**
	 * Ensure that instances of {@link SoundCommentsQuery} are constructed correctly.
	 */
	@Test
	public void soundCommentsQueryCorrectlyConstructed() {
		final SoundCommentsQuery query = new SoundCommentsQuery(SOUND_ID);

		assertTrue(query.getRequestParameters().size() == 0);

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(
				String.valueOf(SOUND_ID), query.getRouteParameters().get(SoundCommentsQuery.SOUND_ID_ROUTE_PARAMETER));
	}
}
