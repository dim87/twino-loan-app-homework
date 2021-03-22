package com.example.twinoloanapphomework.api.loaninvestments.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoanInvestmentRepository extends JpaRepository<LoanInvestment, Long> {

	long countAllByInvestorIdAndCreatedAfter(long investorId, Date date);

	List<LoanInvestment> findAllByInvestorId(long investorId);
}
