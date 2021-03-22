package com.example.twinoloanapphomework.api.loans.impl.exceptions;

public class LoanDateMustBeInFutureException extends RuntimeException {

	public LoanDateMustBeInFutureException() {
		super("Loan date must be in future");
	}
}
