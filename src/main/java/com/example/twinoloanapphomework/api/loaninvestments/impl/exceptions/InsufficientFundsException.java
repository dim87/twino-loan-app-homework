package com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException(final long investorId, final BigDecimal amount) {
		super(String.format("Investor with ID '%s' has insufficient funds to invest amount '%s'", investorId, amount));
	}
}
