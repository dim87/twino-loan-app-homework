package com.example.twinoloanapphomework.api.investors.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.twinoloanapphomework.api.investors.InvestorService;
import com.example.twinoloanapphomework.api.loaninvestments.impl.exceptions.InsufficientFundsException;

@ExtendWith(MockitoExtension.class)
class InvestorServiceImplTest {

	@InjectMocks
	private final InvestorService investorService = new InvestorServiceImpl();

	@Mock
	private InvestorRepository investorRepository;

	@Test
	void cannotDecreaseBalanceWhenInvestorWasNotFound() {
		final long investorId = 1L;
		when(investorRepository.findById(investorId)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> investorService.decreaseBalance(investorId, BigDecimal.valueOf(100L)));
	}

	@Test
	void cannotDecreaseBalanceWhenInsufficientFunds() {
		final long investorId = mockInvestorWithBalance(BigDecimal.valueOf(30L));
		assertThrows(InsufficientFundsException.class, () -> investorService.decreaseBalance(investorId, BigDecimal.valueOf(100L)));
	}

	private long mockInvestorWithBalance(final BigDecimal balance) {
		final long investorId = 1L;
		final Investor investor = new Investor(investorId, "name", "surname", balance);
		when(investorRepository.findById(investorId)).thenReturn(Optional.of(investor));
		return investorId;
	}

	@Test
	void canDecreaseBalance() {
		final long investorId = mockInvestorWithBalance(BigDecimal.valueOf(3L));
		final BigDecimal result = investorService.decreaseBalance(investorId, BigDecimal.ONE);
		assertEquals(BigDecimal.valueOf(2L), result);
	}

	@Test
	void canDecreaseBalanceToZero() {
		final long investorId = mockInvestorWithBalance(BigDecimal.valueOf(3L));
		final BigDecimal result = investorService.decreaseBalance(investorId, BigDecimal.valueOf(3L));
		assertEquals(BigDecimal.ZERO, result);
	}
}
