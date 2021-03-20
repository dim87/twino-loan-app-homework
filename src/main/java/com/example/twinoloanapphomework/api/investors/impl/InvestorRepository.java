package com.example.twinoloanapphomework.api.investors.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InvestorRepository extends JpaRepository<Investor, Long> {

}
