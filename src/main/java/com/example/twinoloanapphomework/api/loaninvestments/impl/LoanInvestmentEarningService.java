package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentEarningTO;
import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.api.utils.DateUtils;

@Service
class LoanInvestmentEarningService {

	private static final int DAYS_IN_MONTH = 30;

	@Autowired
	private LoanService loanService;

	@Transactional(readOnly = true)
	public LoanInvestmentEarningTO getInvestment(final LoanInvestment investment) {
		final long loanId = investment.getLoanId();
		final BigDecimal invested = investment.getAmount();

		final LoanTO loan = loanService.load(loanId);
		final BigDecimal earned = getEarnedAmount(investment.getCreated(), invested, loan.getTerm(), loan.getInterestRatePerMonth());

		return new LoanInvestmentEarningTO(loanId, investment.getId(), invested, earned);
	}

	BigDecimal getEarnedAmount(final Date investedDate, final BigDecimal investedAmount, final Date loanTerm, final BigDecimal interestRatePerMonth) {
		final long daysDifference = getDayDifferenceBetweenInvestmentAndLoanTerm(investedDate, loanTerm);
		final BigDecimal interestPerPeriod = getInterestPerInvestmentPeriod(interestRatePerMonth, daysDifference);

		return investedAmount.multiply(interestPerPeriod).setScale(3, RoundingMode.CEILING);
	}

	private BigDecimal getInterestPerInvestmentPeriod(final BigDecimal interestRatePerMonth, final long daysDifference) {
		final BigDecimal interestPerDay = interestRatePerMonth.setScale(3, RoundingMode.CEILING).divide(BigDecimal.valueOf(DAYS_IN_MONTH), RoundingMode.CEILING);
		return interestPerDay.multiply(BigDecimal.valueOf(daysDifference));
	}

	private long getDayDifferenceBetweenInvestmentAndLoanTerm(final Date investmentDate, final Date loanTerm) {
		return DateUtils.getDaysDifference(investmentDate, loanTerm);
	}
}
