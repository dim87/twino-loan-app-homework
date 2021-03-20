package com.example.twinoloanapphomework.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.investors.InvestorTO;

@RestController
@RequestMapping("/investors")
public class InvestorController {

	@Autowired
	private InvestorService investorService;

	@GetMapping
	public List<InvestorTO> list() {
		return investorService.list();
	}

	@PostMapping
	public InvestorTO create(final @RequestBody @Valid InvestorTO investor) {
		return investorService.create(investor);
	}
}
