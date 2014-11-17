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

/**
 * Object representing the data associated with a single sound instance on freesound.org. Full documentation of the data
 * structure can be found at http://www.freesound.org/docs/api/resources_apiv2.html#user-resources.
 */
public class User {

	/** The URI for this users’ profile on the Freesound website. */
	private String url;

	/** The username. */
	private String username;

	/** The 'about' text of users' profile (if indicated). */
	private String about;

	/** The URI of users' homepage outside Freesound (if indicated). */
	private String homepage;

	/** Dictionary including the URIs for the avatar of the user. The avatar is presented in three sizes Small, Medium
	 * and Large, which correspond to the three fields in the dictionary. If user has no avatar, this field is null. */
	private Map<String, String> avatarURIs;

	/** The date when the user joined Freesound. */
	private Date dateJoined;

	/** The number of sounds uploaded by the user. */
	private Integer numberOfSounds;

	/** The URI for a list of sounds by the user. */
	private String soundsURI;

	/** The number of packs by the user. */
	private Integer numberOfPacks;

	/** The URI for a list of packs by the user. */
	private String packsURI;

	/** The number of forum posts by the user. */
	private Integer numberOfPosts;

	/** The number of comments that user made in other users’ sounds. */
	private Integer numberOfComments;

	/** The URI for a list of bookmark categories by the user. */
	private String bookmarkCategoriesURI;

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
	 * @return the about
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(final String about) {
		this.about = about;
	}

	/**
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * @param homepage the homepage to set
	 */
	public void setHomepage(final String homepage) {
		this.homepage = homepage;
	}

	/**
	 * @return the avatarURIs
	 */
	public Map<String, String> getAvatarURIs() {
		return avatarURIs;
	}

	/**
	 * @param avatarURIs the avatarURIs to set
	 */
	public void setAvatarURIs(final Map<String, String> avatarURIs) {
		this.avatarURIs = avatarURIs;
	}

	/**
	 * @return the dateJoined
	 */
	public Date getDateJoined() {
		return dateJoined;
	}

	/**
	 * @param dateJoined the dateJoined to set
	 */
	public void setDateJoined(final Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	/**
	 * @return the numberOfSounds
	 */
	public Integer getNumberOfSounds() {
		return numberOfSounds;
	}

	/**
	 * @param numberOfSounds the numberOfSounds to set
	 */
	public void setNumberOfSounds(final Integer numberOfSounds) {
		this.numberOfSounds = numberOfSounds;
	}

	/**
	 * @return the soundsURI
	 */
	public String getSoundsURI() {
		return soundsURI;
	}

	/**
	 * @param soundsURI the soundsURI to set
	 */
	public void setSoundsURI(final String soundsURI) {
		this.soundsURI = soundsURI;
	}

	/**
	 * @return the numberOfPacks
	 */
	public Integer getNumberOfPacks() {
		return numberOfPacks;
	}

	/**
	 * @param numberOfPacks the numberOfPacks to set
	 */
	public void setNumberOfPacks(final Integer numberOfPacks) {
		this.numberOfPacks = numberOfPacks;
	}

	/**
	 * @return the packsURI
	 */
	public String getPacksURI() {
		return packsURI;
	}

	/**
	 * @param packsURI the packsURI to set
	 */
	public void setPacksURI(final String packsURI) {
		this.packsURI = packsURI;
	}

	/**
	 * @return the numberOfPosts
	 */
	public Integer getNumberOfPosts() {
		return numberOfPosts;
	}

	/**
	 * @param numberOfPosts the numberOfPosts to set
	 */
	public void setNumberOfPosts(final Integer numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
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
	 * @return the bookmarkCategoriesURI
	 */
	public String getBookmarkCategoriesURI() {
		return bookmarkCategoriesURI;
	}

	/**
	 * @param bookmarkCategoriesURI the bookmarkCategoriesURI to set
	 */
	public void setBookmarkCategoriesURI(final String bookmarkCategoriesURI) {
		this.bookmarkCategoriesURI = bookmarkCategoriesURI;
	}
}
