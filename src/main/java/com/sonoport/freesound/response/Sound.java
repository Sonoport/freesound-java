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

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.sonoport.freesound.License;

/**
 * Object representing the data associated with a single sound instance on freesound.org. Full documentation of the data
 * structure can be found at http://www.freesound.org/docs/api/resources_apiv2.html#sound-resources.
 */
public class Sound {

	/** The sound’s unique identifier. */
	private Integer id;

	/** The URI for this sound on the Freesound website. */
	private String url;

	/** The name user gave to the sound. */
	private String name;

	/** An array of tags the user gave to the sound. */
	private Set<String> tags;

	/** The description the user gave to the sound. */
	private String description;

	/** Latitude and longitude of the geotag separated by spaces (e.g. “41.0082325664 28.9731252193”, only for sounds
	 * that have been geotagged). */
	private String geotag;

	/** The date when the sound was uploaded. */
	private Date created;

	/** The license under which the sound is available. */
	private License license;

	/** The type of sound (wav, aif, aiff, mp3, or flac). */
	private String type;

	/** The number of channels. */
	private Integer channels;

	/** The size of the file in bytes. */
	private Integer filesize;

	/** The bit rate of the sound in kbps. */
	private Integer bitrate;

	/** The bit depth of the sound. */
	private Integer bitdepth;

	/** The duration of the sound in seconds. */
	private Float duration;

	/** The sample rate of the sound. */
	private Float samplerate;

	/** The username of the uploader of the sound. */
	private String username;

	/** If the sound is part of a pack, this URI poIntegers to that pack’s API resource. */
	private String pack;

	/** The URI for retrieving the original sound. */
	private String downloadURI;

	/** The URI for bookmarking the sound. */
	private String bookmarkURI;

	/** Dictionary containing the URIs for mp3 and ogg versions of the sound. The dictionary includes the fields
	 * preview-hq-mp3 and preview-lq-mp3 (for ~128kbps quality and ~64kbps quality mp3 respectively), and preview-hq-ogg
	 * and preview-lq-ogg (for ~192kbps quality and ~80kbps quality ogg respectively). API authentication is required
	 * for retrieving sound previews (Token or OAuth2). */
	private Map<String, String> previews;

	/** Dictionary including the URIs for spectrogram and waveform visualizations of the sound. The dinctionary includes
	 * the fields waveform_l and waveform_m (for large and medium waveform images respectively), and spectral_l and
	 * spectral_m (for large and medium spectrogram images respectively). */
	private Map<String, String> images;

	/** The number of times the sound was downloaded. */
	private Integer numberOfDownloads;

	/** The average rating of the sound. */
	private Float averageRating;

	/** The number of times the sound was rated. */
	private Integer numberOfRatings;

	/** The URI for rating the sound. */
	private String ratingURI;

	/** The URI of a paginated list of the comments of the sound. */
	private String commentsURI;

	/** The number of comments. */
	private Integer numberOfComments;

	/** The URI to comment the sound. */
	private String commentURI;

	/** URI poIntegering to the similarity resource (to get a list of similar sounds). */
	private String similarSoundsURI;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the tags
	 */
	public Set<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(final Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the geotag
	 */
	public String getGeotag() {
		return geotag;
	}

	/**
	 * @param geotag the geotag to set
	 */
	public void setGeotag(final String geotag) {
		this.geotag = geotag;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(final Date created) {
		this.created = created;
	}

	/**
	 * @return the license
	 */
	public License getLicense() {
		return license;
	}

	/**
	 * @param license the license to set
	 */
	public void setLicense(final License license) {
		this.license = license;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * @return the channels
	 */
	public Integer getChannels() {
		return channels;
	}

	/**
	 * @param channels the channels to set
	 */
	public void setChannels(final Integer channels) {
		this.channels = channels;
	}

	/**
	 * @return the filesize
	 */
	public Integer getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(final Integer filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the bitrate
	 */
	public Integer getBitrate() {
		return bitrate;
	}

	/**
	 * @param bitrate the bitrate to set
	 */
	public void setBitrate(final Integer bitrate) {
		this.bitrate = bitrate;
	}

	/**
	 * @return the bitdepth
	 */
	public Integer getBitdepth() {
		return bitdepth;
	}

	/**
	 * @param bitdepth the bitdepth to set
	 */
	public void setBitdepth(final Integer bitdepth) {
		this.bitdepth = bitdepth;
	}

	/**
	 * @return the duration
	 */
	public Float getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(final Float duration) {
		this.duration = duration;
	}

	/**
	 * @return the samplerate
	 */
	public Float getSamplerate() {
		return samplerate;
	}

	/**
	 * @param samplerate the samplerate to set
	 */
	public void setSamplerate(final Float samplerate) {
		this.samplerate = samplerate;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the pack
	 */
	public String getPack() {
		return pack;
	}

	/**
	 * @param pack the pack to set
	 */
	public void setPack(final String pack) {
		this.pack = pack;
	}

	/**
	 * @return the downloadURI
	 */
	public String getDownloadURI() {
		return downloadURI;
	}

	/**
	 * @param downloadURI the downloadURI to set
	 */
	public void setDownloadURI(final String downloadURI) {
		this.downloadURI = downloadURI;
	}

	/**
	 * @return the bookmarkURI
	 */
	public String getBookmarkURI() {
		return bookmarkURI;
	}

	/**
	 * @param bookmarkURI the bookmarkURI to set
	 */
	public void setBookmarkURI(final String bookmarkURI) {
		this.bookmarkURI = bookmarkURI;
	}

	/**
	 * @return the previews
	 */
	public Map<String, String> getPreviews() {
		return previews;
	}

	/**
	 * @param previews the previews to set
	 */
	public void setPreviews(final Map<String, String> previews) {
		this.previews = previews;
	}

	/**
	 * @return the images
	 */
	public Map<String, String> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(final Map<String, String> images) {
		this.images = images;
	}

	/**
	 * @return the numberOfDownloads
	 */
	public Integer getNumberOfDownloads() {
		return numberOfDownloads;
	}

	/**
	 * @param numberOfDownloads the numberOfDownloads to set
	 */
	public void setNumberOfDownloads(final Integer numberOfDownloads) {
		this.numberOfDownloads = numberOfDownloads;
	}

	/**
	 * @return the averageRating
	 */
	public Float getAverageRating() {
		return averageRating;
	}

	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(final Float averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @return the numberOfRatings
	 */
	public Integer getNumberOfRatings() {
		return numberOfRatings;
	}

	/**
	 * @param numberOfRatings the numberOfRatings to set
	 */
	public void setNumberOfRatings(final Integer numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	/**
	 * @return the ratingURI
	 */
	public String getRatingURI() {
		return ratingURI;
	}

	/**
	 * @param ratingURI the ratingURI to set
	 */
	public void setRatingURI(final String ratingURI) {
		this.ratingURI = ratingURI;
	}

	/**
	 * @return the commentsURI
	 */
	public String getCommentsURI() {
		return commentsURI;
	}

	/**
	 * @param commentsURI the commentsURI to set
	 */
	public void setCommentsURI(final String commentsURI) {
		this.commentsURI = commentsURI;
	}

	/**
	 * @return the numberOfComments
	 */
	public Integer getNumberOfComments() {
		return numberOfComments;
	}

	/**
	 * @param numberOfComments the numberOfComments to set
	 */
	public void setNumberOfComments(final Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	/**
	 * @return the commentURI
	 */
	public String getCommentURI() {
		return commentURI;
	}

	/**
	 * @param commentURI the commentURI to set
	 */
	public void setCommentURI(final String commentURI) {
		this.commentURI = commentURI;
	}

	/**
	 * @return the similarSoundsURI
	 */
	public String getSimilarSoundsURI() {
		return similarSoundsURI;
	}

	/**
	 * @param similarSoundsURI the similarSoundsURI to set
	 */
	public void setSimilarSoundsURI(final String similarSoundsURI) {
		this.similarSoundsURI = similarSoundsURI;
	}

}
