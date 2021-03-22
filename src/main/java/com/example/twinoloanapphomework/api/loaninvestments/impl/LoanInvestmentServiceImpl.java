package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentEarningTO;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentTO;
import com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions.InvestmentLimitReachedException;
import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.api.utils.DateUtils;
import com.example.twinoloanapphomework.config.InvestmentAppConfiguration;

@Service
class LoanInvestmentServiceImpl implements LoanInvestmentService {

	@Autowired
	private LoanInvestmentRepository loanInvestmentRepository;

	@Autowired
	private LoanInvestmentMappingService loanInvestmentMappingService;

	@Autowired
	private LoanService loanService;

	@Autowired
	private InvestorService investorService;

	@Autowired
	private InvestmentAppConfiguration investmentAppConfiguration;

	@Transactional(rollbackFor = Exception.class)
	public LoanInvestmentTO invest(@Valid final LoanInvestmentTO loanInvestmentTO) {
		final long loanId = loanInvestmentTO.getLoanId();
		loanService.load(loanId);

		final long investorId = loanInvestmentTO.getInvestorId();
		investorService.load(investorId);

		validateInvestorHasNotReachedInvestmentLimit(investorId);
		investorService.decreaseBalance(investorId, loanInvestmentTO.getAmount());

		return save(loanInvestmentTO);
	}

	private void validateInvestorHasNotReachedInvestmentLimit(final long investorId) {
		final int maxCountPerMonth = investmentAppConfiguration.getMaxCountPerMonth();
		final Date dateOneMonthAgo = DateUtils.getDateOneMonthAgo();
		final long count = loanInvestmentRepository.countAllByInvestorIdAndCreatedAfter(investorId, dateOneMonthAgo);

		if (count >= maxCountPerMonth) {
			throw new InvestmentLimitReachedException(investorId, maxCountPerMonth);
		}
	}

	private LoanInvestmentTO save(final LoanInvestmentTO loanInvestmentTO) {
		final LoanInvestment loanInvestment = loanInvestmentMappingService.convertDto(loanInvestmentTO);
		final LoanInvestment savedInvestment = loanInvestmentRepository.save(loanInvestment);
		return loanInvestmentMappingService.convertEntity(savedInvestment);
	}

	@Transactional(readOnly = true)
	public List<LoanInvestmentEarningTO> listInvestorEarnings(final long investorId) {
		final List<LoanInvestment> loanInvestments = loanInvestmentRepository.findAllByInvestorId(investorId);

		return loanInvestments.stream().map(this::getInvestment).collect(Collectors.toList());
	}

	private LoanInvestmentEarningTO getInvestment(final LoanInvestment investment) {
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
