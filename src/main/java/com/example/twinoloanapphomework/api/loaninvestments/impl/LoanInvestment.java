package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "loan_investments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class LoanInvestment {

	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long investorId;

	private Long loanId;

	private BigDecimal amount;

	private Date created = new Date();
}
