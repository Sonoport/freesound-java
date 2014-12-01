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

import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sonoport.freesound.response.AudioDescriptors;

/**
 * Transform the JSON representation of the available audio descriptors into an {@link AudioDescriptors} object.
 */
public class AudioDescriptorsMapper extends Mapper<JSONObject, AudioDescriptors> {

	@Override
	public AudioDescriptors map(final JSONObject source) {
		final AudioDescriptors audioDescriptors = new AudioDescriptors();

		final JSONObject fixedLengthDescriptors = extractFieldValue(source, "fixed-length", JSONObject.class);
		final JSONArray fixedLengthOneDimensionalDescriptors =
				extractFieldValue(fixedLengthDescriptors, "one-dimensional", JSONArray.class);
		audioDescriptors.setFixedLengthOneDimensional(new TreeSet<>(parseArray(fixedLengthOneDimensionalDescriptors)));

		final JSONArray fixedLengthMultiDimensionalDescriptors =
				extractFieldValue(fixedLengthDescriptors, "multi-dimensional", JSONArray.class);
		audioDescriptors.setFixedLengthMultiDimensional(
				new TreeSet<>(parseArray(fixedLengthMultiDimensionalDescriptors)));

		final JSONArray variableLengthDescriptors = extractFieldValue(source, "variable-length", JSONArray.class);
		audioDescriptors.setVariableLength(new TreeSet<>(parseArray(variableLengthDescriptors)));

		return audioDescriptors;
	}

}
