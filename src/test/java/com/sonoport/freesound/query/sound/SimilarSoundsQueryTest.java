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

import com.sonoport.freesound.query.SoundPagingQueryTest;

/**
 * Unit tests to ensure the correct operation of {@link SimilarSoundsQuery}.
 */
public class SimilarSoundsQueryTest extends SoundPagingQueryTest<SimilarSoundsQuery> {

	/** Sound identifier to use in tests. */
	private static final int SOUND_ID = 12345;

	/**
	 * Ensure that instances of {@link SimilarSoundsQuery} are constructed correctly.
	 */
	@Test
	public void similarSoundsQueryCorrectlyCreated() {
		final SimilarSoundsQuery query = new SimilarSoundsQuery(SOUND_ID);

		assertTrue(query.getRouteParameters().size() == 1);
		assertEquals(
				String.valueOf(SOUND_ID), query.getRouteParameters().get(SimilarSoundsQuery.SOUND_ID_ROUTE_PARAMETER));
	}

	@Override
	protected SimilarSoundsQuery newQueryInstance() {
		return new SimilarSoundsQuery(SOUND_ID);
	}
}
