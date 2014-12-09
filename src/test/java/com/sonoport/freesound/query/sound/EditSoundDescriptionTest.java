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

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of {@link EditSoundDescription}.
 */
public class EditSoundDescriptionTest extends SoundUploadQueryTest<EditSoundDescription> {

	/** Sound id to use in tests. */
	private static final int SOUND_ID = 321;

	/**
	 * Ensure instances of {@link EditSoundDescription} are constructed correctly.
	 */
	@Test
	public void queryCorrectlyCreated() {
		final EditSoundDescription editSoundDescription = newQueryInstance();

		assertEquals(
				String.valueOf(SOUND_ID),
				editSoundDescription.getRouteParameters().get(EditSoundDescription.SOUND_IDENTIFIER_ROUTE_PARAMETER));
	}

	@Override
	protected EditSoundDescription newQueryInstance() {
		return new EditSoundDescription(SOUND_ID, OAUTH_TOKEN);
	}

}
