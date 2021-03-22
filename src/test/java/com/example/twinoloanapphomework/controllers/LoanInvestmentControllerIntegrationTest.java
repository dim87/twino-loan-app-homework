package com.example.twinoloanapphomework.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.twinoloanapphomework.utils.BaseIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoanInvestmentControllerIntegrationTest extends BaseIntegrationTest {

	@Test
	public void listInvestorEarnings() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/loan-investment/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$[0].loanId").value(1))
			.andExpect(jsonPath("$[0].loanInvestmentId").value(1))
			.andExpect(jsonPath("$[0].invested").value(BigDecimal.valueOf(50.0)))
			.andExpect(jsonPath("$[0].earned").value(BigDecimal.valueOf(20.0)))

			.andExpect(jsonPath("$[1].loanId").value(1))
			.andExpect(jsonPath("$[1].loanInvestmentId").value(2))
			.andExpect(jsonPath("$[1].invested").value(BigDecimal.valueOf(50.0)))
			.andExpect(jsonPath("$[1].earned").value(BigDecimal.valueOf(2.5)))

			.andExpect(jsonPath("$[2].loanId").value(1))
			.andExpect(jsonPath("$[2].loanInvestmentId").value(3))
			.andExpect(jsonPath("$[2].invested").value(BigDecimal.valueOf(50.0)))
			.andExpect(jsonPath("$[2].earned").value(BigDecimal.valueOf(95.0)))

			.andExpect(jsonPath("$[3].loanId").value(3))
			.andExpect(jsonPath("$[3].loanInvestmentId").value(5))
			.andExpect(jsonPath("$[3].invested").value(BigDecimal.valueOf(50.0)))
			.andExpect(jsonPath("$[3].earned").value(BigDecimal.valueOf(51.0)));
	}
}
