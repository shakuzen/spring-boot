/*
 * Copyright 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.micrometer.metrics.autoconfigure;

import io.micrometer.core.instrument.binder.jvm.convention.JvmClassCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmClassLoadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmClassUnloadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmCpuCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmCpuLoadMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmCpuTimeMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmMemoryCommittedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmMemoryMaxMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmMemoryUsedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.JvmThreadCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmClassCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmClassLoadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmClassUnloadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmCpuCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmCpuLoadMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmCpuTimeMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmMemoryCommittedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmMemoryMaxMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmMemoryUsedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.micrometer.MicrometerJvmThreadCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmClassCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmClassLoadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmClassUnloadedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmCpuCountMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmCpuLoadMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmCpuTimeMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmMemoryCommittedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmMemoryMaxMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmMemoryUsedMeterConvention;
import io.micrometer.core.instrument.binder.jvm.convention.otel.OpenTelemetryJvmThreadCountMeterConvention;
import org.junit.jupiter.api.Test;

import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SemanticConventionAutoConfiguration}.
 */
class SemanticConventionAutoConfigurationTests {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
		.withConfiguration(AutoConfigurations.of(SemanticConventionAutoConfiguration.class));

	@Test
	void registersMicrometerConventionsByDefault() {
		this.contextRunner.run((context) -> {
			assertThat(context).hasSingleBean(MicrometerJvmMemoryUsedMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmMemoryCommittedMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmMemoryMaxMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmClassCountMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmClassLoadedMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmClassUnloadedMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmCpuCountMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmCpuLoadMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmCpuTimeMeterConvention.class);
			assertThat(context).hasSingleBean(MicrometerJvmThreadCountMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmMemoryUsedMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmMemoryCommittedMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmMemoryMaxMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmClassCountMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmClassLoadedMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmClassUnloadedMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmCpuCountMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmCpuLoadMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmCpuTimeMeterConvention.class);
			assertThat(context).doesNotHaveBean(OpenTelemetryJvmThreadCountMeterConvention.class);
		});
	}

	@Test
	void registersOpenTelemetryConventionsWhenConventionsSetToOpenTelemetry() {
		this.contextRunner.withPropertyValues("management.observations.conventions=opentelemetry").run((context) -> {
			assertThat(context).hasSingleBean(JvmMemoryUsedMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmMemoryUsedMeterConvention.class);
			assertThat(context).hasSingleBean(JvmMemoryCommittedMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmMemoryCommittedMeterConvention.class);
			assertThat(context).hasSingleBean(JvmMemoryMaxMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmMemoryMaxMeterConvention.class);
			assertThat(context).hasSingleBean(JvmClassCountMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmClassCountMeterConvention.class);
			assertThat(context).hasSingleBean(JvmClassLoadedMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmClassLoadedMeterConvention.class);
			assertThat(context).hasSingleBean(JvmClassUnloadedMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmClassUnloadedMeterConvention.class);
			assertThat(context).hasSingleBean(JvmCpuCountMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmCpuCountMeterConvention.class);
			assertThat(context).hasSingleBean(JvmCpuLoadMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmCpuLoadMeterConvention.class);
			assertThat(context).hasSingleBean(JvmCpuTimeMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmCpuTimeMeterConvention.class);
			assertThat(context).hasSingleBean(JvmThreadCountMeterConvention.class)
				.hasSingleBean(OpenTelemetryJvmThreadCountMeterConvention.class);
		});
	}

	@Test
	void allowsCustomMicrometerConventionsToBeUsed() {
		this.contextRunner.withPropertyValues("management.observations.conventions=micrometer")
			.withUserConfiguration(CustomJvmMemoryUsedMeterConventionConfiguration.class)
			.run((context) -> {
				assertThat(context).hasSingleBean(JvmMemoryUsedMeterConvention.class)
					.hasBean("customJvmMemoryUsedMeterConvention");
				assertThat(context).doesNotHaveBean(MicrometerJvmMemoryUsedMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmMemoryCommittedMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmMemoryMaxMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmClassCountMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmClassLoadedMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmClassUnloadedMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmCpuCountMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmCpuLoadMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmCpuTimeMeterConvention.class);
				assertThat(context).hasSingleBean(MicrometerJvmThreadCountMeterConvention.class);
			});
	}

	@Test
	void allowsCustomOpenTelemetryConventionsToBeUsed() {
		this.contextRunner.withPropertyValues("management.observations.conventions=opentelemetry")
			.withUserConfiguration(CustomJvmClassLoadedMeterConventionConfiguration.class)
			.run((context) -> {
				assertThat(context).hasSingleBean(JvmClassLoadedMeterConvention.class)
					.hasBean("customJvmClassLoadedMeterConvention");
				assertThat(context).doesNotHaveBean(OpenTelemetryJvmClassLoadedMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmMemoryUsedMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmMemoryCommittedMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmMemoryMaxMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmClassCountMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmClassUnloadedMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmCpuCountMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmCpuLoadMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmCpuTimeMeterConvention.class);
				assertThat(context).hasSingleBean(OpenTelemetryJvmThreadCountMeterConvention.class);
			});
	}

	@Configuration(proxyBeanMethods = false)
	static class CustomJvmMemoryUsedMeterConventionConfiguration {

		@Bean
		JvmMemoryUsedMeterConvention customJvmMemoryUsedMeterConvention() {
			return JvmMemoryUsedMeterConvention.of("my.memory.used");
		}

	}

	@Configuration(proxyBeanMethods = false)
	static class CustomJvmClassLoadedMeterConventionConfiguration {

		@Bean
		JvmClassLoadedMeterConvention customJvmClassLoadedMeterConvention() {
			return JvmClassLoadedMeterConvention.of("my.classes.loaded");
		}

	}

}
