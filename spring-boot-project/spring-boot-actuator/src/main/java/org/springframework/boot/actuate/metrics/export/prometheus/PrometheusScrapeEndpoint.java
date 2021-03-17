/*
 * Copyright 2012-2021 the original author or authors.
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

package org.springframework.boot.actuate.metrics.export.prometheus;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Set;

import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * {@link Endpoint @Endpoint} that outputs metrics in a format that can be scraped by the
 * Prometheus server.
 *
 * @author Jon Schneider
 * @author Johnny Lim
 * @since 2.0.0
 */
@RestControllerEndpoint(id = "prometheus")
public class PrometheusScrapeEndpoint {

	private final CollectorRegistry collectorRegistry;

	public PrometheusScrapeEndpoint(CollectorRegistry collectorRegistry) {
		this.collectorRegistry = collectorRegistry;
	}

	@GetMapping(produces = {TextFormat.CONTENT_TYPE_004, TextFormat.CONTENT_TYPE_OPENMETRICS_100})
	public ResponseEntity<String> scrape(@RequestParam(name = "includedNames", required = false) Set<String> includedNames,
										 @RequestHeader(name = "Accept", required = false) String acceptHeader) {
		try {
			Writer writer = new StringWriter();
			Enumeration<MetricFamilySamples> samples = (includedNames != null)
					? this.collectorRegistry.filteredMetricFamilySamples(includedNames)
					: this.collectorRegistry.metricFamilySamples();
			String contentType = TextFormat.chooseContentType(acceptHeader);
			TextFormat.writeFormat(contentType, writer, samples);
			return ResponseEntity.ok().contentType(MediaType.valueOf(contentType)).body(writer.toString());
		}
		catch (IOException ex) {
			// This actually never happens since StringWriter::write() doesn't throw any
			// IOException
			throw new RuntimeException("Writing metrics failed", ex);
		}
	}

}
