package com.example.twinoloanapphomework.api.loans.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.api.loans.impl.exceptions.LoanDateMustBeInFutureException;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

	@InjectMocks
	private final LoanService loanService = new LoanServiceImpl();

	@Test
	void testLoanCanBeCreatedOnlyWhenTermIsInFuture() {
		final Date term = new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime();
		final LoanTO loan = new LoanTO(null, BigDecimal.valueOf(10), term, BigDecimal.valueOf(20));
		assertThrows(LoanDateMustBeInFutureException.class, () -> loanService.create(loan));
	}

}
