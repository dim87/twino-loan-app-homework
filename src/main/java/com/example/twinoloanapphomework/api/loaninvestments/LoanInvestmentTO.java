package com.example.twinoloanapphomework.api.loaninvestments;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoanInvestmentTO {

	private Long id;

	@NotNull(message = "loanId is mandatory")
	private Long loanId;

	@NotNull(message = "investorId is mandatory")
	private Long investorId;

	@NotNull(message = "amount is mandatory")
	private BigDecimal amount;
}
