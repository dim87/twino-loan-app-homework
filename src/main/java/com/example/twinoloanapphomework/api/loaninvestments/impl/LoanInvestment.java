package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity(name = "loan_investments")
@Data
class LoanInvestment {

	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long investorId;

	private Long loanId;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	private Date created = new Date();
}
