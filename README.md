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
    <version>0.3.0</version>
</dependency>
```

Create an instance of FreesoundClient using your Client ID and Secret from the [details you registered with Freesound](http://freesound.org/api/apply). Calls to the API are made by building the appropriate Query subclass and passing it to FreesoundClient.executeQuery().

## Authentication

The freesound.org API requires two types of authentication depending on the type of resource requested. Unprotected resources require the Client ID be passed with the request, whereas protected resources (such as user details, uploading/downloading sounds) follows an OAuth2 permissions flow. Requests for the former are handled automatically by the library, whilst the latter require additional steps:

1. Firstly, you should familiarise youself with the [freesound.org authentication process](http://www.freesound.org/docs/api/authentication.html). At present, the library does not provide functionality associated with steps 1 & 2 (acquiring user permissions and an authorisation code), so these will need to be handled by your application.
2. You may also choose to handle the remainder of the OAuth2 flow by making requests to the token endpoint, however the library does provide convenience methods for retrieving an Access Token from an Authorisation Code (FreesoundClient.redeemAuthorisationCodeForAccessToken()), and getting a new token using a Refresh Token (FreesoundClient.refreshAccessToken()).
3. Any requests requiring OAuth credentials will require the Access Token passed in the constructor of the relevant Query object.

## Query Types

All alls to the API to retrieve resources are modelled by subclasses of the `Query` class. The objects created represent a single query and its results once executed. The basic flow that all queries take is as follows:

```java
Query query = new Query();

freesoundClient.executeQuery(query);

Results request = query.getResults();
```

In addition, the following members are provided to determine whether a particular call was successful:

* `.isErrorResponse()` - Whether the response received was an error (i.e Response Code >= 400)
* `.getHttpResponseCode()` - The actual HTTP response code received
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

freesoundClient.executeQuery(soundInstanceQuery);

Sound sound = soundInstanceQuery.getResults();
```

### Sound Analysis

See: http://www.freesound.org/docs/api/resources_apiv2.html#sound-analysis

**Not yet implemented**

### Similar Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#similar-sounds

**Not yet implemented**

### Sound Comments

See: http://www.freesound.org/docs/api/resources_apiv2.html#sound-comments

**Not yet implemented**

### Download Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#download-sound-oauth2-required

The download sound query returns an `InputStream` object containing the binary contents returned as a result of the call. Once the application has finished with this object, it must close it.

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int soundId = 123;
String oauthToken = "...";

DownloadSound downloadSoundQuery = new DownloadSound(soundId, oauthToken);

freesoundClient.executeQuery(downloadSoundQuery);

InputStream is = downloadSoundQuery.getResults();

// Do something with the InputStream

is.close();
```

### Upload Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#upload-sound-oauth2-required

**Not yet implemented**

### Describe Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#describe-sound-oauth2-required

**Not yet implemented**

### Pending Uploads (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#pending-uploads-oauth2-required

**Not yet implemented**

### Edit Sound Description (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#edit-sound-description-oauth2-required

**Not yet implemented**

### Bookmark Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#bookmark-sound-oauth2-required

**Not yet implemented**

### Rate Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#rate-sound-oauth2-required

**Not yet implemented**

### Comment Sound (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#comment-sound-oauth2-required

**Not yet implemented**

### User Instance

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-instance

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserInstanceQuery userInstanceQuery = new UserInstanceQuery(username);

freesoundClient.executeQuery(userInstanceQuery);

User user = userInstanceQuery.getResults();
```

### User Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-sounds

**Not yet implemented**

### User Packs

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-packs

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String username = "testuser";
UserPacksQuery userPacksQuery = new UserPacksQuery(username);

freesoundClient.executeQuery(userPacksQuery);

PagingResponse<Pack> packs = userPacksQuery.getResults();
```

### User Bookmark Categories

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-bookmark-categories

**Not yet implemented**

### User Bookmark Category Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#user-bookmark-category-sounds

**Not yet implemented**

### Pack Instance

See: http://www.freesound.org/docs/api/resources_apiv2.html#pack-instance

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

int packId = 1234;
PackInstanceQuery packInstanceQuery = new PackInstanceQuery(packId);

freesoundClient.executeQuery(packInstanceQuery);

Pack pack = packInstanceQuery.getResults();
```

### Pack Sounds

See: http://www.freesound.org/docs/api/resources_apiv2.html#pack-sounds

**Not yet implemented**

### Download Pack (OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#download-pack-oauth2-required

**Not yet implemented**

### Me (information about user authenticated using OAuth2, OAuth2 required)

See: http://www.freesound.org/docs/api/resources_apiv2.html#me-information-about-user-authenticated-using-oauth2-oauth2-required

```java
FreesoundClient freesoundClient = new FreesoundClient(clientId, clientSecret);

String oauthToken = "..."; // May be retrieved from freesoundClient.redeemAuthorisationCodeForAccessToken() or freesoundClient.refreshAccessToken()
MeQuery meQuery = new MeQuery(oauthToken);

freesoundClient.executeQuery(meQuery);

CurrentUser currentUser = meQuery.getResults();
```

### Available Audio Descriptors

See: http://www.freesound.org/docs/api/resources_apiv2.html#available-audio-descriptors

**Not yet implemented**

