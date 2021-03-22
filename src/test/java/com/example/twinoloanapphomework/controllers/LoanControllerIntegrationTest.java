package com.example.twinoloanapphomework.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.utils.BaseIntegrationTest;
import com.example.twinoloanapphomework.utils.TestDateUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerIntegrationTest extends BaseIntegrationTest {

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
		final Date today = TestDateUtils.getDateNextMonthFromToday();
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
