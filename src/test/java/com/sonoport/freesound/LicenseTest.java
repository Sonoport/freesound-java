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
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test that the custom lookup code in {@link License} is operating correctly.
 */
public class LicenseTest {

	/** Creative Commons 0 URI. */
	private static final String CC_0_URI = "http://creativecommons.org/publicdomain/zero/1.0/";

	/** Creative Commons 0 Description. */
	private static final String CC_0_DESCRIPTION = "Creative Commons 0";

	/** Creative Commons By-Attribution URI. */
	private static final String CC_ATTRIBUTION_URI = "http://creativecommons.org/licenses/by/3.0/";

	/** Creative Commons By-Attribution Description. */
	private static final String CC_ATTRIBUTION_DESCRIPTION = "Attribution";

	/** Creative Commons By-Attribution Non-Commercial URI. */
	private static final String CC_ATTRIBUTION_NC_URI = "http://creativecommons.org/licenses/by-nc/3.0/";

	/** Creative Commons By-Attribution Non-Commercial Description. */
	private static final String CC_ATTRIBUTION_NC_DESCRIPTION = "Attribution Noncommercial";

	/**
	 * Ensure the Creative Commons 0 license is correctly returned.
	 */
	@Test
	public void creativeCommonsZero() {
		assertEquals(License.CC_0, License.fromDescription(CC_0_DESCRIPTION));
		assertEquals(License.CC_0, License.fromURI(CC_0_URI));
	}

	/**
	 * Ensure the Creative Commons By-Attribution license is correctly returned.
	 */
	@Test
	public void creativeCommonsAttribution() {
		assertEquals(License.CC_ATTRIBUTION, License.fromDescription(CC_ATTRIBUTION_DESCRIPTION));
		assertEquals(License.CC_ATTRIBUTION, License.fromURI(CC_ATTRIBUTION_URI));
	}

	/**
	 * Ensure the Creative Commons By-Attribution Non-Commercial license is correctly returned.
	 */
	@Test
	public void creativeCommonsAttributionNonCommercial() {
		assertEquals(License.CC_ATTRIBUTION_NONCOMMERCIAL, License.fromDescription(CC_ATTRIBUTION_NC_DESCRIPTION));
		assertEquals(License.CC_ATTRIBUTION_NONCOMMERCIAL, License.fromURI(CC_ATTRIBUTION_NC_URI));
	}

	/**
	 * Ensure that an empty string being passed to lookup methods does not cause errors.
	 */
	@Test
	public void emptyString() {
		assertNull(License.fromDescription(""));
		assertNull(License.fromURI(""));
	}

	/**
	 * Ensure that a null value being passed to lookup methods does not cause errors.
	 */
	@Test
	public void nullValue() {
		assertNull(License.fromDescription(null));
		assertNull(License.fromURI(null));
	}
}
