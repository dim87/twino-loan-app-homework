package com.example.twinoloanapphomework.config;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "investment")
public class InvestmentAppConfiguration {

	@NotNull
	private int maxCountPerMonth;

	@NotNull
	private String geoipApiUrl;

	@NotNull
	private List<String> geoipAllowedCountries;


	public int getMaxCountPerMonth() {
		return maxCountPerMonth;
	}

	public void setMaxCountPerMonth(final int maxCountPerMonth) {
		this.maxCountPerMonth = maxCountPerMonth;
	}

	public String getGeoipApiUrl() {
		return geoipApiUrl;
	}

	public void setGeoipApiUrl(final String geoipApiUrl) {
		this.geoipApiUrl = geoipApiUrl;
	}

	public List<String> getGeoipAllowedCountries() {
		return geoipAllowedCountries;
	}

	public void setGeoipAllowedCountries(final List<String> geoipAllowedCountries) {
		this.geoipAllowedCountries = geoipAllowedCountries;
	}
}
