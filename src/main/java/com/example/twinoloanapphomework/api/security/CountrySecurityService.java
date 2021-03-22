package com.example.twinoloanapphomework.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.twinoloanapphomework.api.geoip.GeoIpService;
import com.example.twinoloanapphomework.config.InvestmentAppConfiguration;

@Service
class CountrySecurityService {

	@Autowired
	private InvestmentAppConfiguration investmentAppConfiguration;

	@Autowired
	private GeoIpService geoIpService;

	public boolean requestingIpAllowed(final String ip) {
		if (isLocalIp(ip)) {
			return true;
		}

		if (requestIsFromAllowedCountry(ip)) {
			return true;
		}

		return false;
	}

	private boolean isLocalIp(final String ip) {
		return "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip);
	}

	private boolean requestIsFromAllowedCountry(final String ip) {
		final String geoIpCountryCode = geoIpService.getCountryCode(ip);
		return investmentAppConfiguration.getGeoipAllowedCountries().contains(geoIpCountryCode);
	}
}
