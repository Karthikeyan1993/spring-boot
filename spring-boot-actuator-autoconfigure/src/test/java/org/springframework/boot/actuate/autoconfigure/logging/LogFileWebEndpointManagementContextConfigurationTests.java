/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.logging;

import org.junit.Test;

import org.springframework.boot.actuate.logger.LogFileWebEndpoint;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link LogFileWebEndpointManagementContextConfiguration}.
 *
 * @author Andy Wilkinson
 * @author Stephane Nicoll
 * @author Phillip Webb
 */
public class LogFileWebEndpointManagementContextConfigurationTests {

	private WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
			.withUserConfiguration(
					LogFileWebEndpointManagementContextConfiguration.class);

	@Test
	public void logFileWebEndpointIsAutoConfiguredWhenLoggingFileIsSet() {
		this.contextRunner.withPropertyValues("logging.file:test.log").run(
				(context) -> assertThat(context).hasSingleBean(LogFileWebEndpoint.class));
	}

	@Test
	public void logFileWebEndpointIsAutoConfiguredWhenLoggingPathIsSet() {
		this.contextRunner.withPropertyValues("logging.path:test/logs").run(
				(context) -> assertThat(context).hasSingleBean(LogFileWebEndpoint.class));
	}

	@Test
	public void logFileWebEndpointIsAutoConfiguredWhenExternalFileIsSet() {
		this.contextRunner
				.withPropertyValues("endpoints.logfile.external-file:external.log")
				.run((context) -> assertThat(context)
						.hasSingleBean(LogFileWebEndpoint.class));
	}

	@Test
	public void logFileWebEndpointCanBeDisabled() {
		this.contextRunner
				.withPropertyValues("logging.file:test.log",
						"endpoints.logfile.enabled:false")
				.run((context) -> assertThat(context)
						.hasSingleBean(LogFileWebEndpoint.class));
	}

}
