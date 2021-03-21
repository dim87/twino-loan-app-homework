package com.example.twinoloanapphomework.api.security;

import org.springframework.stereotype.Service;

@Service
class CountrySecurityService {

	public boolean requestingIpAllowed(final String ip) {
		if (isLocalIp(ip)) {
			return true;
		}


		return false;
	}

	private boolean isLocalIp(final String ip) {
		return "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip);
	}
}
