package com.example.twinoloanapphomework.api.loans;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanTO {

	private Long id;

	@NotNull(message = "amount is mandatory")
	private BigDecimal amount;

	@NotNull(message = "term is mandatory")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date term;

	@NotNull(message = "interestRatePerMonth is mandatory")
	private BigDecimal interestRatePerMonth;
}
