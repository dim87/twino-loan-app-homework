package com.example.twinoloanapphomework.api.loans;

import java.util.List;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

@Validated
public interface LoanService {

	List<LoanTO> list();

	LoanTO create(@Valid LoanTO loan);

	LoanTO load(long loanId);
}
