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

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.micrometer.metrics.autoconfigure.jvm.JvmMetricsAutoConfiguration;
import org.springframework.boot.micrometer.metrics.autoconfigure.system.SystemMetricsAutoConfiguration;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for semantic conventions for metrics
 * and observations.
 *
 * @since 4.1.0
 */
@AutoConfiguration(before = { JvmMetricsAutoConfiguration.class, SystemMetricsAutoConfiguration.class })
@EnableConfigurationProperties(ObservationProperties.class)
public final class SemanticConventionAutoConfiguration {

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnProperty(prefix = "management.observations", name = "conventions", havingValue = "micrometer",
			matchIfMissing = true)
	static class MicrometerSemanticConventionConfiguration {

		@Bean
		@ConditionalOnMissingBean(JvmMemoryUsedMeterConvention.class)
		MicrometerJvmMemoryUsedMeterConvention micrometerJvmMemoryUsedMeterConvention() {
			return new MicrometerJvmMemoryUsedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmMemoryCommittedMeterConvention.class)
		MicrometerJvmMemoryCommittedMeterConvention micrometerJvmMemoryCommittedMeterConvention() {
			return new MicrometerJvmMemoryCommittedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmMemoryMaxMeterConvention.class)
		MicrometerJvmMemoryMaxMeterConvention micrometerJvmMemoryMaxMeterConvention() {
			return new MicrometerJvmMemoryMaxMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassCountMeterConvention.class)
		MicrometerJvmClassCountMeterConvention micrometerJvmClassCountMeterConvention() {
			return new MicrometerJvmClassCountMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassLoadedMeterConvention.class)
		MicrometerJvmClassLoadedMeterConvention micrometerJvmClassLoadedMeterConvention() {
			return new MicrometerJvmClassLoadedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassUnloadedMeterConvention.class)
		MicrometerJvmClassUnloadedMeterConvention micrometerJvmClassUnloadedMeterConvention() {
			return new MicrometerJvmClassUnloadedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuCountMeterConvention.class)
		MicrometerJvmCpuCountMeterConvention micrometerJvmCpuCountMeterConvention() {
			return new MicrometerJvmCpuCountMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuLoadMeterConvention.class)
		MicrometerJvmCpuLoadMeterConvention micrometerJvmCpuLoadMeterConvention() {
			return new MicrometerJvmCpuLoadMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuTimeMeterConvention.class)
		MicrometerJvmCpuTimeMeterConvention micrometerJvmCpuTimeMeterConvention() {
			return new MicrometerJvmCpuTimeMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmThreadCountMeterConvention.class)
		MicrometerJvmThreadCountMeterConvention micrometerJvmThreadCountMeterConvention() {
			return new MicrometerJvmThreadCountMeterConvention();
		}

	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnProperty(prefix = "management.observations", name = "conventions", havingValue = "opentelemetry")
	static class OpenTelemetrySemanticConventionConfiguration {

		@Bean
		@ConditionalOnMissingBean(JvmMemoryUsedMeterConvention.class)
		OpenTelemetryJvmMemoryUsedMeterConvention openTelemetryJvmMemoryUsedMeterConvention() {
			return new OpenTelemetryJvmMemoryUsedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmMemoryCommittedMeterConvention.class)
		OpenTelemetryJvmMemoryCommittedMeterConvention openTelemetryJvmMemoryCommittedMeterConvention() {
			return new OpenTelemetryJvmMemoryCommittedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmMemoryMaxMeterConvention.class)
		OpenTelemetryJvmMemoryMaxMeterConvention openTelemetryJvmMemoryMaxMeterConvention() {
			return new OpenTelemetryJvmMemoryMaxMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassCountMeterConvention.class)
		OpenTelemetryJvmClassCountMeterConvention openTelemetryJvmClassCountMeterConvention() {
			return new OpenTelemetryJvmClassCountMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassLoadedMeterConvention.class)
		OpenTelemetryJvmClassLoadedMeterConvention openTelemetryJvmClassLoadedMeterConvention() {
			return new OpenTelemetryJvmClassLoadedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmClassUnloadedMeterConvention.class)
		OpenTelemetryJvmClassUnloadedMeterConvention openTelemetryJvmClassUnloadedMeterConvention() {
			return new OpenTelemetryJvmClassUnloadedMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuCountMeterConvention.class)
		OpenTelemetryJvmCpuCountMeterConvention openTelemetryJvmCpuCountMeterConvention() {
			return new OpenTelemetryJvmCpuCountMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuLoadMeterConvention.class)
		OpenTelemetryJvmCpuLoadMeterConvention openTelemetryJvmCpuLoadMeterConvention() {
			return new OpenTelemetryJvmCpuLoadMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmCpuTimeMeterConvention.class)
		OpenTelemetryJvmCpuTimeMeterConvention openTelemetryJvmCpuTimeMeterConvention() {
			return new OpenTelemetryJvmCpuTimeMeterConvention();
		}

		@Bean
		@ConditionalOnMissingBean(JvmThreadCountMeterConvention.class)
		OpenTelemetryJvmThreadCountMeterConvention openTelemetryJvmThreadCountMeterConvention() {
			return new OpenTelemetryJvmThreadCountMeterConvention();
		}

	}

}
