freesound-java
==============
[ ![Codeship Status for Sonoport/freesound-java](https://codeship.com/projects/6b0c6770-4be5-0132-dce8-16e44e5e051f/status)](https://codeship.com/projects/46881) [![Coverage Status](https://img.shields.io/coveralls/Sonoport/freesound-java.svg)](https://coveralls.io/r/Sonoport/freesound-java)

Java library for accessing the freesound.org API. 

Made available under the Apache License, Version 2.0.

# Usage

Releases of the library are available through the Maven Central repository at the following coordinates:

```xml
<dependency>
    <groupId>com.sonoport</groupId>
    <artifactId>freesound-java</artifactId>
    <version>0.6.0</version>
</dependency>
```

Create an instance of FreesoundClient using your Client ID and Secret from the [details you registered with Freesound](http://freesound.org/api/apply). Calls to the API are made by building the appropriate Query subclass and passing it to FreesoundClient.executeQuery().

## Authentication

The freesound.org API requires two types of authentication depending on the type of resource requested. Unprotected resources require the Client ID be passed with the request, whereas protected resources (such as user details, uploading/downloading sounds) follows an OAuth2 permissions flow. Requests for the former are handled automatically by the library, whilst the latter require additional steps:

1. Firstly, you should familiarise youself with the [freesound.org authentication process](http://www.freesound.org/docs/api/authentication.html). At present, the library does not provide functionality associated with steps 1 & 2 (acquiring user permissions and an authorisation code), so these will need to be handled by your application.
2. You may also choose to handle the remainder of the OAuth2 flow by making requests to the token endpoint, however the library does provide convenience methods for retrieving an Access Token from an Authorisation Code (FreesoundClient.redeemAuthorisationCodeForAccessToken()), and getting a new token using a Refresh Token (FreesoundClient.refreshAccessToken()).
3. Any requests requiring OAuth credentials will require the Access Token passed in the constructor of the relevant Query object.

## Query Types

All alls to the API to retrieve resources are modelled by subclasses of the `Query` class. `FreesoundClient` is then used to execute the query, and returns a `Response` object encapsulating the results. The basic flow that all queries take is as follows:

```java
Query query = new Query();

Response response = freesoundClient.executeQuery(query);

int httpStatusCode = response.getResponseStatus();
Results results = response.getResults();
```

In addition, the following members are provided to determine whether a particular call was successful:

* `.isErrorResponse()` - Whether the response received was an error (i.e Response Code >= 400)
* `.getResponseStatus()` - The actual HTTP response code received
* `.getResponseStatusString()` - The message associated with the HTTP status code (e.g. for a 200 response, this value would be 'OK')
* `.getErrorDetails()` - The error message recieved from freesound.org, if any. This is the contents of the `details` field in the JSON error response.

### Text Search

See: http://www.freesound.org/docs/api/resources_apiv2.html#text-search

The library offers multiple ways of constructing a text search query, depending on the complexity required. At its most simple, to search for a string using default options, the class constructor can be used:

```java
TextSearch textSearch = new TextSearch("Search String");
```

For more complex scenarios, a fluent builder is offered for the various options:

* To specify the search string: `.searchString(String)`
* To specify filters: `.filter(SearchFilter)`
* To specify sort order: `.sortOrder(SortOrder)`
* To specify whether results should be grouped by pack: `.groupByPack(boolean)`
* To specify the fields to return in the results: `.includeField(String)` or `.includeFields(Set<String>)`
* To specify the number of results to return per page: `.pageSize(int)` (Maximum of 150)
* To specify the page of results to retrieve: `.page(int)`

As an example:

```java
final TextSearch textSearch = new TextSearch().searchString("cars").sortOrder(SortOrder.SCORE).includeFields(fields).groupByPack(true).pageSize(50).page(3);
```

As the results of search queries can have multiple pages of results, the methoods `.hasNextPage()` and `.hasPreviousPage()` are provided to determine whether it is possible to move forward or backwards through the results. To retrieve the next or previous page of results, the `TextSearch` object is passed to either `FreesoundClient.nextPage()` or `FreesoundClient.previousPage()` to refresh the results.

### Content Search

See: http://www.freesound.org/docs/api/resources_apiv2.html#content-search

**Not yet implemented**

### Combined Search

See: http://www.freesound.org/docs/api/resources_apiv2.html#combined-search

**Not yet implemented**

### Sound Instance

See: http://www.freesound.org/docs/api/resources_apiv2.html#sound-instance

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundIdentifier = 123;
SoundInstanceQuery soundInstanceQuery = new SoundInstanceQuery(soundIdentifier);

Response<Sound> response = freesoundClient.executeQuery(soundInstanceQuery);

```

### Sound Analysis

See: http://www.freesound.org/docs/api/resources_apiv2.html#sound-analysis

**Not yet implemented**

### Similar Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#similar-sounds

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundIdentifier = 123;
SimilarSoundsQuery similarSoundsQuery = new SimilarSoundsQuery(soundIdentifier);

PagingResponse<Sound> response = freesoundClient.executeQuery(similarSoundsQuery);
```

### Sound Comments

See: http://www.freesound.org/docs/api/resources_apiv2.html#sound-comments

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundId = 123;
SoundCommentsQuery soundCommentsQuery = new SoundCommentsQuery(soundId);

PagingResponse<Comment> response = freesoundClient.executeQuery(soundCommentsQuery);
```

### Download Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#download-sound-oauth2-required

The download sound query returns an `InputStream` object containing the binary contents returned as a result of the call. Once the application has finished with this object, it must close it.

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundId = 123;
String oauthToken = "...";

DownloadSound downloadSoundQuery = new DownloadSound(soundId, oauthToken);

Response<InputStream> response = freesoundClient.executeQuery(downloadSoundQuery);

InputStream is = response.getResults();

// Do something with the InputStream

is.close();
```

### Upload Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#upload-sound-oauth2-required

There are two constructors that can be used to create a request to upload a sound, depending on how much information the user wishes to provide initially:

```java
File soundFile = new File(...);
String oauthToken = "...";

UploadSound uploadRequest = new UploadSound(soundFile, oauthToken);
```

Sounds uploaded using this form will require further details to be provided using the 'Describe Sound' functionality and, as such, will appear in the 'Pending Description' section of thelist of pending uploads. The second form includes the required description elements, so will automatically progress to the freesound moderation phase:

```java
File soundFile = new File(...);
String description = "...";
License license = ...;
Set<String> tags = new HashSet<>();
String oauthToken = "...";

UploadSound uploadRequest = new UploadSound(soundFile, description, license, tags, oauthToken);
```

In addition, the class provides a fluent builder approach for adding additional elements:

* Sound Name: `.name(String)`
* Sound Description: `.description(String)`
* Sound License: `.license(License)`
* User Pack: `.pack(String)`
* Sound Tags: `.tag(String)` and/or `.tags(Collection<String>)`
* Geospatial Details: `.geotag(Geotag)`

### Describe Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#describe-sound-oauth2-required

The Describe Sound functionality works in a similar manner to the Upload functionality, and provides the same fluent builder methods described above. The constructor is as follows:

```java
String soundFileName = "..."; // Name of the previously uploaded sound file
String description = "...";
License license = ...;
Set<String> tags = new HashSet<>();
String oauthToken = "...";

DescribeSound describeSound = new DescribeSound(soundFileName, description, license, tags, oauthToken);
```

### Pending Uploads (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#pending-uploads-oauth2-required

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String oauthToken = "...";

PendingUploadsQuery pendingUploadsQuery = new PendingUploadsQuery(oauthToken);

Response<PendingUploads> response = freesoundClient.executeQuery(pendingUploadsQuery);
```

### Edit Sound Description (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#edit-sound-description-oauth2-required

The Edit Sound functionality takes a minimal constructor, and then makes use of the same fluent builder methods as Upload Sound and Describe Sound. Only the elements specified will be updated - anything not explitly included will remain unchanged.

```java
int soundId = 123;
String oauthToken = "...";

EditSoundDescription editSoundDescription = new EditSoundDescription(soundId, oauthToken);
```

### Bookmark Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#bookmark-sound-oauth2-required

There are two ways to create `BookmarkSound` query objects - by using the simple constructor and the Fluent API:

```java
int soundId = 123;
String oauthToken = "...";
String bookmarkName = "Bookmark Name";
String bookmarkCategory = "category";

BookmarkSound bookmarkQuery = new BookmarkSound(soundId, oauthToken).name(bookmarkName).category(bookmarkCategory);
```

Or, alternatively, specifying everything in the constructor:

```java
int soundId = 123;
String oauthToken = "...";
String bookmarkName = "Bookmark Name";
String bookmarkCategory = "category";

BookmarkSound bookmarkQuery = new BookmarkSound(soundId, oauthToken, bookmarkName, bookmarkCategory);
```

The result returned is a `String` containing the message received from the API.

Note that, at present, there is a bug in the API that means the bookmark name and category MUST be specified, otherwise an HTTP 500 Internal Server Error is returned. These parameters should be optional (as specified in the API documentation), so a bug has been raised on GitHub to cover this: https://github.com/MTG/freesound/issues/642. Until this is resolved, use of the 'full' 4 parameter constructor is recommended.

### Rate Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#rate-sound-oauth2-required

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundId = 123;
int rating = 5;
String oauthToken = "...";

RateSound rateSoundQuery = new RateSound(soundId, rating, oauthToken);

Response<String> response = freesoundClient.executeQuery(rateSoundQuery);
```

Note that ratings must be between 0 and 5 (inclusive), otherwise an `IllegalArgumentException` will be thrown.

### Comment Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#comment-sound-oauth2-required

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundId = 123;
String comment = "...";
String oauthToken = "...";

CommentSound commentSoundQuery = new CommentSound(soundId, comment, oauthToken);

Response<String> response = freesoundClient.executeQuery(commentSoundQuery);
```

### User Instance

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-instance

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserInstanceQuery userInstanceQuery = new UserInstanceQuery(username);

Response<User> response = freesoundClient.executeQuery(userInstanceQuery);
```

### User Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-sounds

`UserSoundsQuery` allows you to specify which fields are returned for the sounds `Sound` records using the `.includeField(String)` and `.includeFields(Set<String>)` methods

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
Set<String> fieldsToInclude = new HashSet<>(Arrays.asList("id", "name", ...etc...));

UserSoundsQuery userSoundsQuery = new UserSoundsQuery(username).includeFields(fieldsToInclude);

PagingResponse<Sound> response = freesoundClient.executeQuery(userSoundsQuery);
```

### User Packs

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-packs

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserPacksQuery userPacksQuery = new UserPacksQuery(username);

PagingResponse<Sound> response = freesoundClient.executeQuery(userPacksQuery);
```

### User Bookmark Categories

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-bookmark-categories

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserBookmarkCategoriesQuery query = new UserBookmarkCategoriesQuery(username);

PagingResponse<BookmarkCategory> response = executeQuery(query);
```

### User Bookmark Category Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-bookmark-category-sounds

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
int categoryId = 123;
UserBookmarkCategorySoundsQuery query = new UserBookmarkCategorySoundsQuery(username, categoryId);

PagingResponse<Sound> response = executeQuery(query);
```

Or, if you want to retrieve bookmarked sounds that have not yet been categorised by the user:

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserUncategorisedBookmarkedSoundsQuery query = new UserUncategorisedBookmarkedSoundsQuery(username);

PagingResponse<Sound> response = executeQuery(query);
```

### Pack Instance

See: http://www.freesound.org/docs/api/resources_apiv2.html#pack-instance

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int packId = 1234;
PackInstanceQuery packInstanceQuery = new PackInstanceQuery(packId);

Response<Pack> response = freesoundClient.executeQuery(packInstanceQuery);
```

### Pack Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#pack-sounds

`PackSoundsQuery` allows you to optionally specify which fields are returned for the sounds `Sound` records using the `.includeField(String)` and `.includeFields(Set<String>)` methods

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int packId = 123;
Set<String> fieldsToInclude = new HashSet<>(Arrays.asList("id", "name", ...etc...));

PackSoundsQuery packSoundsQuery = new PackSoundsQuery(packId).includeFields(fieldsToInclude);

PagingResponse<Sound> response = freesoundClient.executeQuery(packSoundsQuery);
```

### Download Pack (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#download-pack-oauth2-required

The download pack query returns an `InputStream` object containing the binary contents returned as a result of the call - this consists of a ZIP compressed object containing all of the sounds in the pack. Once the application has finished with this object, it must close it.

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int packId = 123;
String oauthToken = "...";

DownloadPack downloadPackQuery = new DownloadPack(packId, oauthToken);

Response<InputStream> response = freesoundClient.executeQuery(downloadPackQuery);

InputStream is = response.getResults();

// Do something with the InputStream

is.close();
```

### Me (information about user authenticated using OAuth2, OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#me-information-about-user-authenticated-using-oauth2-oauth2-required

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String oauthToken = "..."; // May be retrieved from freesoundClient.redeemAuthorisationCodeForAccessToken() or freesoundClient.refreshAccessToken()
MeQuery meQuery = new MeQuery(oauthToken);

Response<CurrentUser> response = freesoundClient.executeQuery(meQuery);
```

### Available Audio Descriptors

See: http://www.freesound.org/docs/api/resources_apiv2.html#available-audio-descriptors

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

AvailableAudioDescriptorsQuery query = new AvailableAudioDescriptorsQuery();

Response<AudioDescriptors> response = freesoundClient.executeQuery(query);
```


