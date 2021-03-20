package com.example.twinoloanapphomework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentTO;

@RestController
@RequestMapping("/loan-investment")
public class LoanInvestmentController {

	@Autowired
	private LoanInvestmentService loanInvestmentService;

	@PostMapping
	public LoanInvestmentTO create(final @RequestBody @Valid LoanInvestmentTO loanInvestmentTO) {
		return loanInvestmentService.invest(loanInvestmentTO);
	}
}
