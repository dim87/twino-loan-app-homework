package com.example.twinoloanapphomework.api.investors.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.investors.InvestorTO;

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
}
