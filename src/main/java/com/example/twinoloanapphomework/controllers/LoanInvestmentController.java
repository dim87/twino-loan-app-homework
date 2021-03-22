package com.example.twinoloanapphomework.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentEarningTO;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentService;
import com.example.twinoloanapphomework.api.loaninvestments.LoanInvestmentTO;

@RestController
@RequestMapping("/loan-investment")
public class LoanInvestmentController {

	@Autowired
	private LoanInvestmentService loanInvestmentService;

	// todo: should be closed behind authorization
	// @PreAuthorize("hasRole('ROLE_INVESTOR')")
	@GetMapping(value = "/{investorId}")
	public List<LoanInvestmentEarningTO> listInvestorEarnings(final @PathVariable long investorId) {
		return loanInvestmentService.listInvestorEarnings(investorId);
	}

	@PostMapping
	public LoanInvestmentTO create(final @RequestBody @Valid LoanInvestmentTO loanInvestmentTO) {
		return loanInvestmentService.invest(loanInvestmentTO);
	}
}
