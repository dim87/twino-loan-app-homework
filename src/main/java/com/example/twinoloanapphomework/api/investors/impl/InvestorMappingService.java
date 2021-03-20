package com.example.twinoloanapphomework.api.investors.impl;

import org.springframework.stereotype.Service;

import com.example.twinoloanapphomework.api.investors.InvestorTO;
import com.example.twinoloanapphomework.api.mapping.MappingService;

@Service
class InvestorMappingService extends MappingService<Investor, InvestorTO> {

	@Override
	protected Class<Investor> getEntityClass() {
		return Investor.class;
	}

	@Override
	protected Class<InvestorTO> getDtoClass() {
		return InvestorTO.class;
	}
}
