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
package com.sonoport.freesound.response.mapping;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;

import com.sonoport.freesound.response.Comment;

/**
 * Unit tests to ensure the correct operation of {@link CommentMapper}.
 *
 * Source data for tests can be found at <code>/src/test/resources/comment.json</code>.
 */
public class CommentMapperTest extends MapperTest {

	/** The username in the comment. */
	private static final String USERNAME = "cjrowe";

	/** The comment itself. */
	private static final String COMMENT = "This is great!";

	/** Date/time the comment was made. */
	private static final Date CREATED = new Date(1417016748000L);

	/** Instance of {@link CommentMapper} to use in tests. */
	private final CommentMapper mapper = new CommentMapper();

	/**
	 * Ensure that {@link CommentMapper} correctly processes a valid JSON representation of a comment.
	 *
	 * @throws Exception Any exceptions thrown in test
	 */
	@Test
	public void parseComment() throws Exception {
		final JSONObject commentJSON = readJSONFile("/comment.json");

		final Comment comment = mapper.map(commentJSON);

		assertEquals(USERNAME, comment.getUsername());
		assertEquals(COMMENT, comment.getComment());
		assertEquals(CREATED, comment.getCreated());
	}
}
