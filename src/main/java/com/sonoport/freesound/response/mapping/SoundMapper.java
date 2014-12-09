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

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sonoport.freesound.License;
import com.sonoport.freesound.response.Sound;

/**
 * Map the freesound.org JSON representation of an individual sound instance to a {@link Sound} DTO.
 */
public class SoundMapper extends Mapper<JSONObject, Sound> {

	@Override
	public Sound map(final JSONObject source) {
		final Sound sound = new Sound();

		sound.setId(extractFieldValue(source, "id", Integer.class));
		sound.setUrl(extractFieldValue(source, "url", String.class));
		sound.setName(extractFieldValue(source, "name", String.class));
		sound.setTags(new HashSet<String>(parseArray(extractFieldValue(source, "tags", JSONArray.class))));
		sound.setDescription(extractFieldValue(source, "description", String.class));
		sound.setGeotag(extractFieldValue(source, "geotag", String.class));
		sound.setCreated(parseDate(extractFieldValue(source, "created", String.class)));
		sound.setLicense(License.fromURI(extractFieldValue(source, "license", String.class)));
		sound.setType(extractFieldValue(source, "type", String.class));
		sound.setChannels(extractFieldValue(source, "channels", Integer.class));
		sound.setFilesize(extractFieldValue(source, "filesize", Integer.class));
		sound.setBitrate(extractFieldValue(source, "bitrate", Integer.class));
		sound.setBitdepth(extractFieldValue(source, "bitdepth", Integer.class));
		sound.setDuration(extractFieldValue(source, "duration", Float.class));
		sound.setSamplerate(extractFieldValue(source, "samplerate", Float.class));
		sound.setUsername(extractFieldValue(source, "username", String.class));
		sound.setPack(extractFieldValue(source, "pack", String.class));
		sound.setDownloadURI(extractFieldValue(source, "download", String.class));
		sound.setBookmarkURI(extractFieldValue(source, "bookmark", String.class));
		sound.setPreviews(parseDictionary(extractFieldValue(source, "previews", JSONObject.class)));
		sound.setImages(parseDictionary(extractFieldValue(source, "images", JSONObject.class)));
		sound.setNumberOfDownloads(extractFieldValue(source, "num_downloads", Integer.class));
		sound.setAverageRating(extractFieldValue(source, "avg_rating", Float.class));
		sound.setNumberOfRatings(extractFieldValue(source, "num_ratings", Integer.class));
		sound.setRatingURI(extractFieldValue(source, "rate", String.class));
		sound.setCommentsURI(extractFieldValue(source, "comments", String.class));
		sound.setNumberOfComments(extractFieldValue(source, "num_comments", Integer.class));
		sound.setCommentURI(extractFieldValue(source, "comment", String.class));
		sound.setSimilarSoundsURI(extractFieldValue(source, "similar_sounds", String.class));

		return sound;
	}

}
