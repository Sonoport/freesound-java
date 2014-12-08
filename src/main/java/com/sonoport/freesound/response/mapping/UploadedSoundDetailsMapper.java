/*
 * Copyright 2014 Sonoport (Asia) Pte Ltd
 *
 * import org.json.JSONObject;

import com.sonoport.freesound.response.UploadedSoundDetails;
le except in compliance with the License.
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

import org.json.JSONObject;

import com.sonoport.freesound.response.UploadedSoundDetails;

/**
 * {@link Mapper} used to convert the JSON response after uploading a sound to a {@link UploadedSoundDetails} object.
 */
public class UploadedSoundDetailsMapper extends Mapper<JSONObject, UploadedSoundDetails> {

	@Override
	public UploadedSoundDetails map(final JSONObject source) {
		final UploadedSoundDetails uploadedSoundDetails = new UploadedSoundDetails();

		uploadedSoundDetails.setDetail(extractFieldValue(source, "detail", String.class));
		uploadedSoundDetails.setId(extractFieldValue(source, "id", Integer.class));
		uploadedSoundDetails.setFilename(extractFieldValue(source, "filename", String.class));

		return uploadedSoundDetails;
	}

}
