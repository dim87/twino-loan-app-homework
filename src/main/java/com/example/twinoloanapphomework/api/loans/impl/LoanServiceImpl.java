package com.example.twinoloanapphomework.api.loans.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public List<LoanTO> list() {
		final PageRequest pageRequest = PageRequest.of(0, 100);
		final List<Loan> loans = loanRepository.findAll(pageRequest).getContent();
		return mapToTransfer(loans);
	}

	private List<LoanTO> mapToTransfer(final List<Loan> loans) {
		return loans.stream().map(item -> modelMapper.map(item, LoanTO.class)).collect(Collectors.toList());
	}
}
