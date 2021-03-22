package com.example.twinoloanapphomework.utils;

import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseIntegrationTest {

	@LocalServerPort
	protected Integer port;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	protected final ObjectMapper mapper = new ObjectMapper();

	@BeforeAll
	public static void initializeTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

}
