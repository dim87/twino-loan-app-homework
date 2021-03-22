package com.example.twinoloanapphomework.api.geoip;

import java.net.URI;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.twinoloanapphomework.config.InvestmentAppConfiguration;

@Service
public class GeoIpService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeoIpService.class);

	private static final String DEFAULT_COUNTRY_CODE = "LV";

	@Autowired
	private InvestmentAppConfiguration investmentAppConfiguration;

	private final RestTemplate restTemplate = new RestTemplate();

	public String getCountryCode(final String ip) {
		final URI apiUri = buildRequestUri(ip);

		try {
			final ResponseEntity<GeoIpResponse> response =
				restTemplate.exchange(apiUri, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<GeoIpResponse>() {});

			final HttpStatus statusCode = response.getStatusCode();
			if (statusCode.is2xxSuccessful()) {
				return extractCountryCodeFromResponse(response.getBody());
			}
		} catch (final Exception e) {
			LOGGER.warn("GeoIP service call failed", e);
		}

		return getDefaultCountryCode();
	}

	private String extractCountryCodeFromResponse(final GeoIpResponse response) {
		if (Objects.isNull(response.getCountryCode()) || Objects.equals(response.getCountryCode(), "")) {
			return getDefaultCountryCode();
		}

		return response.getCountryCode();
	}

	private String getDefaultCountryCode() {
		return DEFAULT_COUNTRY_CODE;
	}

	private URI buildRequestUri(final String ip) {
		return UriComponentsBuilder.fromHttpUrl(investmentAppConfiguration.getGeoipApiUrl()).pathSegment(ip).build().toUri();
	}
}
