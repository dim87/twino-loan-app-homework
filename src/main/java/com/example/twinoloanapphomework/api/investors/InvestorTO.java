package com.example.twinoloanapphomework.api.investors;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InvestorTO {

	private Long id;

	@NotBlank(message = "name is mandatory")
	private String name;

	@NotBlank(message = "surname is mandatory")
	private String surname;

	@NotNull(message = "balance is mandatory")
	private BigDecimal balance;
}
