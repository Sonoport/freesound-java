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

import java.util.List;

/**
 * Class representing the response from the 'Pending Uploads' API endpoint. Provides details of sounds that have been
 * uploaded by the current user, but there not yet available publicly on the service.
 *
 * Full details of the data structure are available at:
 * http://www.freesound.org/docs/api/resources_apiv2.html#pending-uploads-oauth2-required
 */
public class PendingUploads {

	/** List of names of sounds that do not yet have descriptions associated with them. */
	private List<String> pendingDescription;

	/** Collection of uploaded sounds that have not yet been processed by the system. */
	private List<Sound> pendingProcessing;

	/** Collection of uploaded sounds that have not yet passed through the moderation process. */
	private List<Sound> pendingModeration;

	/**
	 * @return the pendingDescription
	 */
	public List<String> getPendingDescription() {
		return pendingDescription;
	}

	/**
	 * @param pendingDescription the pendingDescription to set
	 */
	public void setPendingDescription(final List<String> pendingDescription) {
		this.pendingDescription = pendingDescription;
	}

	/**
	 * @return the pendingProcessing
	 */
	public List<Sound> getPendingProcessing() {
		return pendingProcessing;
	}

	/**
	 * @param pendingProcessing the pendingProcessing to set
	 */
	public void setPendingProcessing(final List<Sound> pendingProcessing) {
		this.pendingProcessing = pendingProcessing;
	}

	/**
	 * @return the pendingModeration
	 */
	public List<Sound> getPendingModeration() {
		return pendingModeration;
	}

	/**
	 * @param pendingModeration the pendingModeration to set
	 */
	public void setPendingModeration(final List<Sound> pendingModeration) {
		this.pendingModeration = pendingModeration;
	}
}
