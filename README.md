freesound-java
==============
[ ![Codeship Status for Sonoport/freesound-java](https://codeship.com/projects/6b0c6770-4be5-0132-dce8-16e44e5e051f/status)](https://codeship.com/projects/46881) [![Coverage Status](https://img.shields.io/coveralls/Sonoport/freesound-java.svg)](https://coveralls.io/r/Sonoport/freesound-java)

Java library for accessing the freesound.org API

# Usage

Create an instance of FreesoundClient using your Client ID and Secret from the [details you registered with Freesound](http://freesound.org/api/apply). Calls to the API are made by building the appropriate Query subclass and passing it to FreesoundClient.executeQuery().
