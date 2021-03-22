package com.example.twinoloanapphomework.api.loans.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.api.loans.impl.exceptions.LoanDateMustBeInFutureException;

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
		validateLoanTermIsInFuture(loan.getTerm());

		final Loan mappedEntity = loanMappingService.convertDto(loan);
		final Loan savedEntity = loanRepository.save(mappedEntity);
		return loanMappingService.convertEntity(savedEntity);
	}

	private void validateLoanTermIsInFuture(final Date term) {
		final Date today = new Date();
		if (today.after(term)) {
			throw new LoanDateMustBeInFutureException();
		}
	}

	@Transactional(readOnly = true)
	public LoanTO load(final long loanId) {
		return loanRepository.findById(loanId)
			.map(loan -> loanMappingService.convertEntity(loan))
			.orElseThrow(() -> new EntityNotFoundException(String.format("loan with ID '%s' was not found", loanId)));
	}
}
