package com.example.twinoloanapphomework.api.loaninvestments;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanInvestmentEarningTO {

	private Long loanId;
	private Long loanInvestmentId;
	private BigDecimal invested;
	private BigDecimal earned;
}
