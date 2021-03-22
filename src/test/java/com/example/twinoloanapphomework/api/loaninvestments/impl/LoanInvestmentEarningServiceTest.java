package com.example.twinoloanapphomework.api.loaninvestments.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanInvestmentEarningServiceTest {

	@InjectMocks
	private LoanInvestmentEarningService loanInvestmentEarningService;


	@Test
	void testEarnedAmountPerMonthCalculation() {
		final Date investedDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final BigDecimal investedAmount = BigDecimal.valueOf(100);
		final Date loanTerm = new GregorianCalendar(2021, Calendar.MARCH, 1).getTime();
		final BigDecimal interestRatePerMonth = BigDecimal.valueOf(1.5);

		final BigDecimal earnedAmount = loanInvestmentEarningService.getEarnedAmount(investedDate, investedAmount, loanTerm, interestRatePerMonth);
		assertEquals(BigDecimal.valueOf(140.0).setScale(3, RoundingMode.CEILING), earnedAmount);
	}

	@Test
	void testEarnedAmountPerDayCalculation() {
		final Date investedDate = new GregorianCalendar(2021, Calendar.FEBRUARY, 1).getTime();
		final BigDecimal investedAmount = BigDecimal.valueOf(100);
		final Date loanTerm = new GregorianCalendar(2021, Calendar.FEBRUARY, 2).getTime();
		final BigDecimal interestRatePerMonth = BigDecimal.valueOf(1.5);

		final BigDecimal earnedAmount = loanInvestmentEarningService.getEarnedAmount(investedDate, investedAmount, loanTerm, interestRatePerMonth);
		assertEquals(BigDecimal.valueOf(5).setScale(3, RoundingMode.CEILING), earnedAmount);
	}
}
