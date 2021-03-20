package com.example.twinoloanapphomework.api.investors.impl;

import java.math.BigDecimal;
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

@Entity(name = "investors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class Investor {

	@Id
	@Column(name = "id", columnDefinition = "bigserial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String surname;

	private BigDecimal balance;
}
