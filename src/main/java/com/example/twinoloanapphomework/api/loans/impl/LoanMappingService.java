package com.example.twinoloanapphomework.api.loans.impl;

import org.springframework.stereotype.Service;

import com.example.twinoloanapphomework.api.loans.LoanTO;
import com.example.twinoloanapphomework.api.mapping.MappingService;

@Service
class LoanMappingService extends MappingService<Loan, LoanTO> {

	@Override
	protected Class<Loan> getEntityClass() {
		return Loan.class;
	}

	@Override
	protected Class<LoanTO> getDtoClass() {
		return LoanTO.class;
	}
}
