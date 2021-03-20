package com.example.twinoloanapphomework.api.investors;

import java.util.List;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

@Validated
public interface InvestorService {

	List<InvestorTO> list();

	InvestorTO create(@Valid InvestorTO investor);
}
