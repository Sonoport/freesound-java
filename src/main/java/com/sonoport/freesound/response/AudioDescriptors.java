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
package com.sonoport.freesound.response;

import java.util.Set;

/**
 * Collections of descriptors that can be used in content searches and filters. For full details see
 * http://www.freesound.org/docs/api/resources_apiv2.html#available-audio-descriptors.
 */
public class AudioDescriptors {

	/** Fixed length descriptors that take a single value (e.g. pitch). */
	private Set<String> fixedLengthOneDimensional;

	/** Fixed length descriptors that take a multiple values (e.g. mfcc). */
	private Set<String> fixedLengthMultiDimensional;

	/** Descriptors that have different values depending on the sound. */
	private Set<String> variableLength;

	/**
	 * @return the fixedLengthOneDimensional
	 */
	public Set<String> getFixedLengthOneDimensional() {
		return fixedLengthOneDimensional;
	}

	/**
	 * @param fixedLengthOneDimensional the fixedLengthOneDimensional to set
	 */
	public void setFixedLengthOneDimensional(final Set<String> fixedLengthOneDimensional) {
		this.fixedLengthOneDimensional = fixedLengthOneDimensional;
	}

	/**
	 * @return the fixedLengthMultiDimensional
	 */
	public Set<String> getFixedLengthMultiDimensional() {
		return fixedLengthMultiDimensional;
	}

	/**
	 * @param fixedLengthMultiDimensional the fixedLengthMultiDimensional to set
	 */
	public void setFixedLengthMultiDimensional(
			final Set<String> fixedLengthMultiDimensional) {
		this.fixedLengthMultiDimensional = fixedLengthMultiDimensional;
	}

	/**
	 * @return the variableLength
	 */
	public Set<String> getVariableLength() {
		return variableLength;
	}

	/**
	 * @param variableLength the variableLength to set
	 */
	public void setVariableLength(final Set<String> variableLength) {
		this.variableLength = variableLength;
	}
}
