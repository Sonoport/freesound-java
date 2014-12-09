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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.sonoport.freesound.License;
import com.sonoport.freesound.query.HTTPRequestMethod;
import com.sonoport.freesound.query.JSONResponseQuery;
import com.sonoport.freesound.query.OAuthQuery;
import com.sonoport.freesound.response.mapping.Mapper;

/**
 * Abstract class containing common code for queries that involve working with uploaded files. Provides fluent
 * builder-type methods for constructing queries.
 *
 * @param <R> The response type
 * @param <T> The type of the extending subclass
 */
public abstract class AbstractSoundUploadQuery<R extends Object, T extends AbstractSoundUploadQuery<R, T>>
				extends JSONResponseQuery<R> implements OAuthQuery {

	/** Parameter to pass the sound description through as. */
	protected static final String DESCRIPTION_PARAMETER_NAME = "description";

	/** Parameter to pass the license through as. */
	protected static final String LICENSE_PARAMETER_NAME = "license";

	/** Parameter to pass the tags through as. */
	protected static final String TAGS_PARAMETER_NAME = "tags";

	/** Parameter to pass the geotag through as. */
	protected static final String GEOTAG_PARAMETER_NAME = "geotag";

	/** Parameter to pass the pack name through as. */
	protected static final String PACK_PARAMETER_NAME = "pack";

	/** Parameter to pass the sound name through as. */
	protected static final String SOUND_NAME_PARAMETER_NAME = "name";

	/** The name that will be given to the sound. If not provided, filename will be used. */
	private String name;

	/** The tags that will be assigned to the sound. */
	private Set<String> tags;

	/** A textual description of the sound. */
	private String description;

	/** The license of the sound. */
	private License license;

	/** The name of the pack where the sound should be included. If user has created no such pack with that name, a new
	 * one will be created. */
	private String pack;

	/** Geotag information for the sound. */
	private Geotag geotag;

	/** OAuth2 Access Token to present with request. */
	private final String oauthToken;

	/**
	 * @param path Path to API endpoint
	 * @param oauthToken OAuth2 access token to present
	 * @param responseMapper {@link Mapper} used to convert results
	 */
	protected AbstractSoundUploadQuery(
			final String path, final String oauthToken, final Mapper<JSONObject, R> responseMapper) {
		super(HTTPRequestMethod.POST, path, responseMapper);

		this.oauthToken = oauthToken;
	}

	@Override
	public Map<String, Object> getQueryParameters() {
		final Map<String, Object> queryParams = new HashMap<>();

		if (name != null) {
			queryParams.put(SOUND_NAME_PARAMETER_NAME, name);
		}

		if (description != null) {
			queryParams.put(DESCRIPTION_PARAMETER_NAME, description);
		}

		if (license != null) {
			queryParams.put(LICENSE_PARAMETER_NAME, license.getDescription());
		}

		if ((tags != null) && !tags.isEmpty()) {
			final StringBuilder tagsString = new StringBuilder();
			for (final String tag : tags) {
				tagsString.append(tag.replaceAll("\\s", "-"));
				tagsString.append(' ');
			}

			queryParams.put(TAGS_PARAMETER_NAME, tagsString.toString().trim());
		}

		if (pack != null) {
			queryParams.put(PACK_PARAMETER_NAME, pack);
		}

		if (geotag != null) {
			queryParams.put(
					GEOTAG_PARAMETER_NAME,
					geotag.getLatitude() + "," + geotag.getLongitude() + "," + geotag.getZoom());
		}

		return queryParams;
	}

	/**
	 * Specify a description for the sound being uploaded.
	 *
	 * @param description The description of the sound
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T description(final String description) {
		this.description = description;
		return (T) this;
	}

	/**
	 * Specify the license associated with the sound.
	 *
	 * @param license The sound license
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T license(final License license) {
		this.license = license;
		return (T) this;
	}

	/**
	 * Specify an individual tag associated with the sound.
	 *
	 * @param tag The tag associated with the sound
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T tag(final String tag) {
		if (this.tags == null) {
			this.tags = new HashSet<>();
		}

		this.tags.add(tag);
		return (T) this;
	}

	/**
	 * Specify the tags associated with the sound.
	 *
	 * @param tags The tags associated with the sound
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T tags(final Collection<String> tags) {
		if (this.tags == null) {
			this.tags = new HashSet<>();
		}

		this.tags.addAll(tags);

		return (T) this;
	}

	/**
	 * Specify the name of the sound.
	 *
	 * @param name Name of the sound
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T name(final String name) {
		this.name = name;
		return (T) this;
	}

	/**
	 * Specify the pack the sound should be included in. The pack will be created if it does not already exist.
	 *
	 * @param pack Name of the pack
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T pack(final String pack) {
		this.pack = pack;
		return (T) this;
	}

	/**
	 * Specify geolocation details associated with the sound.
	 *
	 * @param geotag Geo details for sound
	 * @return The current {@link UploadSound} instance
	 */
	@SuppressWarnings("unchecked")
	public T geotag(final Geotag geotag) {
		this.geotag = geotag;
		return (T) this;
	}

	@Override
	public final String getOauthToken() {
		return oauthToken;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(final String name) {
		this.name = name;
	}

	/**
	 * @param tags the tags to set
	 */
	protected void setTags(final Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * @param description the description to set
	 */
	protected void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param license the license to set
	 */
	protected void setLicense(final License license) {
		this.license = license;
	}

	/**
	 * @param pack the pack to set
	 */
	protected void setPack(final String pack) {
		this.pack = pack;
	}

	/**
	 * @param geotag the geotag to set
	 */
	protected void setGeotag(final Geotag geotag) {
		this.geotag = geotag;
	}

}
