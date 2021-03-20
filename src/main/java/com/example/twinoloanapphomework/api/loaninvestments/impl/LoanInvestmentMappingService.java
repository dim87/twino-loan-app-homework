package com.example.twinoloanapphomework.api.loaninvestments.impl;

import org.springframework.stereotype.Service;

import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentTO;
import com.example.twinoloanapphomework.api.mapping.MappingService;

@Service
class LoanInvestmentMappingService extends MappingService<LoanInvestment, LoanInvestmentTO> {

	@Override
	protected Class<LoanInvestment> getEntityClass() {
		return LoanInvestment.class;
	}

	@Override
	protected Class<LoanInvestmentTO> getDtoClass() {
		return LoanInvestmentTO.class;
	}
}
