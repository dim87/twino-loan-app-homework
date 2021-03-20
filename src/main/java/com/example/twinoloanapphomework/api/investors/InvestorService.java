package com.example.twinoloanapphomework.api.investors;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

@Validated
public interface InvestorService {

	List<InvestorTO> list();

	InvestorTO create(@Valid InvestorTO investor);

	InvestorTO load(long investorId);

	void decreaseBalance(long investorId, BigDecimal amount);
}
