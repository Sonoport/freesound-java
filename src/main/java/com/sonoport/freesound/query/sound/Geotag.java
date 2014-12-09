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

/**
 * Geolocation details associated with a sound.
 */
public class Geotag {

	/** Latitude of location. */
	private final double latitude;

	/** Longitude of location. */
	private final double longitude;

	/** Zoom level of location. */
	private final int zoom;

	/**
	 * @param latitude Latitude of location
	 * @param longitude Longitude of location
	 * @param zoom Zoom level of location
	 */
	public Geotag(final double latitude, final double longitude, final int zoom) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.zoom = zoom;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the zoom
	 */
	public int getZoom() {
		return zoom;
	}
}
