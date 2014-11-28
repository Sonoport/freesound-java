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
package com.sonoport.freesound.query.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Unit tests to ensure the correct operation of {@link SearchFilter}s.
 */
public class SearchFilterTest {

	/** Test field name. */
	private static final String FIELD_1 = "field1";

	/** Test field value. */
	private static final String VALUE_1 = "value1";

	/** Test field name. */
	private static final String FIELD_2 = "field2";

	/** Test field value. */
	private static final String VALUE_2 = "value2";

	/**
	 * Test the custom equals() method on {@link SearchFilter}.
	 */
	@Test
	public void testEquals() {
		final SearchFilter searchFilter1 = new SearchFilter(FIELD_1, VALUE_1);
		final SearchFilter duplicateSearchFilter1 = new SearchFilter(FIELD_1, VALUE_1);
		final SearchFilter searchFilter2 = new SearchFilter(FIELD_2, VALUE_2);

		assertEquals(searchFilter1, duplicateSearchFilter1);
		assertEquals(searchFilter1, searchFilter1);
		assertNotEquals(searchFilter1, searchFilter2);
		assertNotEquals(searchFilter1, null);
	}
}
