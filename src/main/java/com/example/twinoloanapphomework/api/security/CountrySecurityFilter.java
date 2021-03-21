package com.example.twinoloanapphomework.api.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class CountrySecurityFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountrySecurityFilter.class);

	@Autowired
	private CountrySecurityService countrySecurityService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest)request;
		final String address = getClientAddress(httpRequest);
		if (!countrySecurityService.requestingIpAllowed(address)) {
			LOGGER.warn("IP '{}' is not allowed to use the service", address);
			final HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		filterChain.doFilter(request, response);
	}

	private String getClientAddress(final HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		return ipAddress == null ? request.getRemoteAddr() : ipAddress;
	}
}
