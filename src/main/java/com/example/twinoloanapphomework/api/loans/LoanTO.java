package com.example.twinoloanapphomework.api.loans;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoanTO {

	private Long id;

	@NotNull(message = "amount is mandatory")
	private BigDecimal amount;

	@NotNull(message = "term is mandatory")
	private Date term;

	@NotNull(message = "interestRatePerMonth is mandatory")
	private BigDecimal interestRatePerMonth;
}
