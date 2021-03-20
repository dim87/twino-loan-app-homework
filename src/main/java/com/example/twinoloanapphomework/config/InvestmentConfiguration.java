package com.example.twinoloanapphomework.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "investment")
public class InvestmentConfiguration {

	@NotNull
	private int maxCountPerMonth;

	public int getMaxCountPerMonth() {
		return maxCountPerMonth;
	}

	public void setMaxCountPerMonth(final int maxCountPerMonth) {
		this.maxCountPerMonth = maxCountPerMonth;
	}
}
