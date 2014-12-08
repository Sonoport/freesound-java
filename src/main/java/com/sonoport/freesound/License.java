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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Enumeration used to represent each of the license types in use on freesound.org. The license representation varies in
 * different parts of the API, so this enum provides a canonical view of the different licenses for use by the library.
 */
public enum License {

	/** Creative Commons 0 (Public Domain) license. */
	CC_0("Creative Commons 0", "http://creativecommons.org/publicdomain/zero/"),

	/** Creative Commons By-Attribution license. */
	CC_ATTRIBUTION("Attribution", "http://creativecommons.org/licenses/by/"),

	/** Creative Commons By-Attribution Non-commercial license. */
	CC_ATTRIBUTION_NONCOMMERCIAL("Attribution Noncommercial", "http://creativecommons.org/licenses/by-nc/");

	/** {@link Map} used to lookup the {@link License} type by the textual description. */
	private static final Map<String, License> LICENSE_BY_DESCRIPTION;
	static {
		LICENSE_BY_DESCRIPTION = new HashMap<String, License>();

		for (final License license : License.values()) {
			LICENSE_BY_DESCRIPTION.put(license.getDescription(), license);
		}
	}

	/**
	 * {@link Map} used to lookup the {@link License} type based on the URI. As different versions of the same license
	 * exist (and additional versions may be added in the future), we hold the common prefix as the key. By testing the
	 * received URI against the key using {@link String#startsWith(String)}, we can be version-agnostic.
	 */
	private static final Map<String, License> LICENSE_BY_URI_PREFIX;
	static {
		LICENSE_BY_URI_PREFIX = new HashMap<String, License>();

		for (final License license : License.values()) {
			LICENSE_BY_URI_PREFIX.put(license.getURIPrefix(), license);
		}
	}

	/** The textual representation of the license used in the API. */
	private String description;

	/** Prefix of the URI(s) used for this license type. */
	private String uriPrefix;

	/**
	 * @param description The textual representation of the license
	 * @param uriPrefix Prefix of the URI(s)
	 */
	private License(final String description, final String uriPrefix) {
		this.description = description;
		this.uriPrefix = uriPrefix;
	}

	/**
	 * Lookup the appropriate {@link License} enum based on the description.
	 *
	 * @param description Description of the license
	 * @return The relevant {@link License} enum
	 */
	public static License fromDescription(final String description) {
		return LICENSE_BY_DESCRIPTION.get(description);
	}

	/**
	 * Lookup the appropriate {@link License} enum based on the URI.
	 *
	 * @param uri The URI of the license
	 * @return The relevant {@link License} enum
	 */
	public static License fromURI(final String uri) {
		License license = null;

		if (uri != null) {
			for (final Entry<String, License> entry : LICENSE_BY_URI_PREFIX.entrySet()) {
				if (uri.startsWith(entry.getKey())) {
					license = entry.getValue();
					break;
				}
			}
		}
		return license;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the uriPrefix
	 */
	public String getURIPrefix() {
		return uriPrefix;
	}
}
