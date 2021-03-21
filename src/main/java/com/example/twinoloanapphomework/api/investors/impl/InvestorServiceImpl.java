package com.example.twinoloanapphomework.api.investors.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.investors.InvestorTO;
import com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions.InsufficientFundsException;

@Service
class InvestorServiceImpl implements InvestorService {

	@Autowired
	private InvestorRepository investorRepository;

	@Autowired
	private InvestorMappingService investorMappingService;

	@Transactional(readOnly = true)
	public List<InvestorTO> list() {
		final PageRequest pageRequest = PageRequest.of(0, 100);
		final List<Investor> loans = investorRepository.findAll(pageRequest).getContent();
		return investorMappingService.convertEntity(loans);
	}

	@Transactional(rollbackFor = Exception.class)
	public InvestorTO create(final InvestorTO investor) {
		final Investor mappedEntity = investorMappingService.convertDto(investor);
		final Investor savedEntity = investorRepository.save(mappedEntity);
		return investorMappingService.convertEntity(savedEntity);
	}

	@Transactional(readOnly = true)
	public InvestorTO load(final long investorId) {
		return investorRepository.findById(investorId)
			.map(loan -> investorMappingService.convertEntity(loan))
			.orElseThrow(() -> new EntityNotFoundException(String.format("investor with ID '%s' was not found", investorId)));
	}

	@Transactional(rollbackFor = Exception.class)
	public BigDecimal decreaseBalance(final long investorId, final BigDecimal amount) {
		final Investor investor = investorRepository.findById(investorId)
			.orElseThrow(() -> new EntityNotFoundException(String.format("investor with ID '%s' was not found", investorId)));

		if (!hasSufficientFunds(amount, investor)) {
			throw new InsufficientFundsException(investorId, amount);
		}

		final BigDecimal newBalance = investor.getBalance().subtract(amount);

		investor.setBalance(newBalance);
		investorRepository.save(investor);

		return newBalance;
	}

	private boolean hasSufficientFunds(final BigDecimal amount, final Investor investor) {
		return investor.getBalance().compareTo(amount) > -1;
	}
}
