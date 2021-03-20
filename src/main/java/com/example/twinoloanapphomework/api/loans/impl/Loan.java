package com.example.twinoloanapphomework.api.loans.impl;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "loans")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class Loan {

	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date term;

	private BigDecimal interestRatePerMonth;
}
