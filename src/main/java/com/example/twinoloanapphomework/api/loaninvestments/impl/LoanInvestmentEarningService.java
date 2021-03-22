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

	@Autowired
	private LoanService loanService;

	@Transactional(readOnly = true)
	public LoanInvestmentEarningTO getInvestment(final LoanInvestment investment) {
		final long loanId = investment.getLoanId();
		final BigDecimal invested = investment.getAmount();
		final BigDecimal earned = getEarnedAmount(investment, loanId, invested);
		return new LoanInvestmentEarningTO(loanId, investment.getId(), invested, earned);
	}

	private BigDecimal getEarnedAmount(final LoanInvestment investment, final long loanId, final BigDecimal invested) {
		final LoanTO loan = loanService.load(loanId);

		final long daysDifference = getDayDifferenceBetweenInvestmentAndLoanTerm(investment.getCreated(), loan.getTerm());
		final BigDecimal interestPerPeriod = getInterestPerInvestmentPeriod(loan.getInterestRatePerMonth(), daysDifference);

		return invested.multiply(interestPerPeriod).setScale(3, RoundingMode.CEILING);
	}

	private BigDecimal getInterestPerInvestmentPeriod(final BigDecimal interestRatePerMonth, final long daysDifference) {
		final BigDecimal interestPerDay = interestRatePerMonth.divide(BigDecimal.valueOf(30), RoundingMode.CEILING);
		return interestPerDay.multiply(BigDecimal.valueOf(daysDifference));
	}

	private long getDayDifferenceBetweenInvestmentAndLoanTerm(final Date investmentDate, final Date loanTerm) {
		return DateUtils.getDaysDifference(investmentDate, loanTerm);
	}
}
