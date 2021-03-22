package com.example.twinoloanapphomework.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.utils.TestDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerIntegrationTest {

	@LocalServerPort
	private Integer port;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	final ObjectMapper mapper = new ObjectMapper();

	@BeforeAll
	public static void initializeTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void userRequestsListsOfLoansSuccessfully() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/loans"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$[0].amount").value(BigDecimal.valueOf(100.0)))
			.andExpect(jsonPath("$[0].term").value("2021-05-09"))
			.andExpect(jsonPath("$[0].interestRatePerMonth").value(BigDecimal.valueOf(1.5)))

			.andExpect(jsonPath("$[1].amount").value(BigDecimal.valueOf(100.5)))
			.andExpect(jsonPath("$[1].term").value("2021-06-09"))
			.andExpect(jsonPath("$[1].interestRatePerMonth").value(BigDecimal.valueOf(1.250)))

			.andExpect(jsonPath("$[2].amount").value(BigDecimal.valueOf(300.0)))
			.andExpect(jsonPath("$[2].term").value("2021-07-09"))
			.andExpect(jsonPath("$[2].interestRatePerMonth").value(BigDecimal.valueOf(1.0)));
	}

	@Test
	public void userRequestsCreationOfLoanSuccessfully() throws Exception {
		final BigDecimal amount = BigDecimal.valueOf(201.34);
		final BigDecimal interest = BigDecimal.valueOf(10.12);
		final Date today = new Date();
		final LoanTO loan = new LoanTO(null, amount, today, interest);

		this.mockMvc
			.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/loans")
				.content(mapper.writeValueAsString(loan))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.amount").value(amount))
			.andExpect(jsonPath("$.term").value(TestDateUtils.formatDate(today)))
			.andExpect(jsonPath("$.interestRatePerMonth").value(interest));
	}

	@Test
	public void userRequestsCreationOfLoanWithoutInfoThrowsValidationError() throws Exception {
		final LoanTO loan = new LoanTO(null, null, null, null);

		this.mockMvc
			.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/loans")
				.content(mapper.writeValueAsString(loan))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is4xxClientError())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.amount").value("amount is mandatory"))
			.andExpect(jsonPath("$.term").value("term is mandatory"))
			.andExpect(jsonPath("$.interestRatePerMonth").value("interestRatePerMonth is mandatory"));
	}
}
