package com.example.twinoloanapphomework.api.loans.impl;

import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;

@Service
class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private LoanMappingService loanMappingService;

	@Transactional(readOnly = true)
	public List<LoanTO> list() {
		final PageRequest pageRequest = PageRequest.of(0, 100);
		final List<Loan> loans = loanRepository.findAll(pageRequest).getContent();
		return loanMappingService.convertEntity(loans);
	}

	@Transactional(rollbackFor = Exception.class)
	public LoanTO create(final LoanTO loan) {
		final Loan mappedEntity = loanMappingService.convertDto(loan);
		final Loan savedEntity = loanRepository.save(mappedEntity);
		return loanMappingService.convertEntity(savedEntity);
	}

	@Transactional(readOnly = true)
	public LoanTO load(final long loanId) {
		return loanRepository.findById(loanId)
			.map(loan -> loanMappingService.convertEntity(loan))
			.orElseThrow(() -> new EntityNotFoundException(String.format("loan with ID '%s' was not found", loanId)));
	}
}
