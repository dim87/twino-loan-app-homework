package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.util.Date;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentTO;
import com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions.InvestmentLimitReachedException;
import com.example.twinoloanapphomework.api.loans.LoanService;
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
}
