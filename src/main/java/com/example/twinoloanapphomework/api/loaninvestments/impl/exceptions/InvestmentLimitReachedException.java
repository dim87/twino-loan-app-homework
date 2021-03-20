package com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions;

public class InvestmentLimitReachedException extends RuntimeException {

	public InvestmentLimitReachedException(final long investorId, final int limit) {
		super(String.format("Investor with ID '%s' has reached investment count limit('%s') this month", investorId, limit));
	}
}
