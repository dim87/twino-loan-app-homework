package com.example.twinoloanapphomework.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twinoloanapphomework.api.loans.LoanService;
import com.example.twinoloanapphomework.api.loans.LoanTO;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@GetMapping
	public List<LoanTO> list() {
		return loanService.list();
	}

	@PostMapping
	public LoanTO create(final @RequestBody @Valid LoanTO loan) {
		return loanService.create(loan);
	}
}
