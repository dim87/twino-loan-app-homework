package com.example.twinoloanapphomework.api.loaninvestments;

import java.util.List;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

@Validated
public interface LoanInvestmentService {

	LoanInvestmentTO invest(@Valid LoanInvestmentTO loanInvestmentTO);

	List<LoanInvestmentEarningTO> listInvestorEarnings(long investorId);
}
