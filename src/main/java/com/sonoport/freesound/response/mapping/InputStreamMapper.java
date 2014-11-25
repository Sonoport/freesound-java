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

import java.io.InputStream;

/**
 * Simple {@link Mapper} class that takes the {@link InputStream} received from the HTTP request, and passes it straight
 * through the the requesting client.
 */
public class InputStreamMapper extends Mapper<InputStream, InputStream> {

	@Override
	public InputStream map(final InputStream source) {
		return source;
	}

}
