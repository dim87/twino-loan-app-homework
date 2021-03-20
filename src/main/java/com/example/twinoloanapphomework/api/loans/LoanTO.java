package com.example.twinoloanapphomework.api.loans;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoanTO {

	private Long id;
	private BigDecimal amount;
	private Date term;
	private BigDecimal interestRatePerMonth;
}
