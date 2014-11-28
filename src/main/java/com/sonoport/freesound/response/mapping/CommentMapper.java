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

import org.json.JSONObject;

import com.sonoport.freesound.response.Comment;

/**
 * {@link Mapper} subclass used to process the JSON representation of a comment on the freesound platform into a
 * {@link Comment} DTO.
 */
public class CommentMapper extends Mapper<JSONObject, Comment> {

	@Override
	public Comment map(final JSONObject source) {
		final Comment comment = new Comment();

		comment.setUsername(extractFieldValue(source, "username", String.class));
		comment.setComment(extractFieldValue(source, "comment", String.class));
		comment.setCreated(parseDate(extractFieldValue(source, "created", String.class)));

		return comment;
	}

}
