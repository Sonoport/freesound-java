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
package com.sonoport.freesound;

import java.io.IOException;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.mashape.unirest.http.Unirest;

/**
 * Unit tests to ensure the correct operation of {@link FreesoundClient}.
 */
public class FreesoundClientTest {

	/** Client identifier to use when constructing the instance of {@link FreesoundClient}. */
	private static final String CLIENT_ID = "client-id";

	/** Client secret to use when constructing the instance of {@link FreesoundClient}. */
	private static final String CLIENT_SECRET = "foobarbaz";

	/** Instance of {@link FreesoundClient} to use in unit tests. */
	private FreesoundClient freesoundClient;

	/**
	 * Configure the instance of {@link FreesoundClient} with its dependencies.
	 */
	@Before
	public void configureClient() {
		freesoundClient = new FreesoundClient(CLIENT_ID, CLIENT_SECRET);
	}

	/**
	 * Ensure calls to {@link FreesoundClient#shutdown()} correctly close down all background processes. This is
	 * primarily aimed at ensuring that {@link Unirest#shutdown()} is called.
	 *
	 * @param mockUnirest Mocking of {@link Unirest#shutdown()} method
	 * @throws Exception Any exceptions thrown during test
	 */
	@SuppressWarnings("static-access")
	@Test
	public void shutdownClient(@Mocked("shutdown") final Unirest mockUnirest) throws Exception {
		freesoundClient.shutdown();

		new Verifications() {
			{
				mockUnirest.shutdown();
			}
		};
	}

	/**
	 * Ensure that a {@link FreesoundClientException} is thrown should any errors be encountered when calling
	 * {@link FreesoundClient#shutdown()} is called.
	 *
	 * @param mockUnirest Mocking of {@link Unirest#shutdown()} method
	 * @throws Exception Any exceptions thrown during test
	 */
	@SuppressWarnings("static-access")
	@Test (expected = FreesoundClientException.class)
	public void unirestShutdownFails(@Mocked("shutdown") final Unirest mockUnirest) throws Exception {
		new Expectations() {
			{
				mockUnirest.shutdown(); result = new IOException();
			}
		};

		freesoundClient.shutdown();
	}
}
