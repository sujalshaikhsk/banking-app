package com.strickers.bankingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strickers.bankingapp.dto.BankResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to do test operation for getting bank and
 *              branch name with ifscCode.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class BankServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImplTest.class);
	@InjectMocks
	BankServiceImpl bankService;
	@Mock
	BankRepository bankRepository;
	BankResponseDto bankResponseDto = new BankResponseDto();
	Bank bank = new Bank();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		bank.setIfscCode("SBI01122");
		bankResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		bankResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);

	}

	@Test
	public void testGetBankAndBranchName() {
		logger.debug("Inside getBankAndBranchNameTest");
		when(bankRepository.findByIfscCode("SBI01122")).thenReturn(bank);
		BankResponseDto bankResponseDto = bankService.getBankAndBranchName("SBI01122");
		assertEquals(200, bankResponseDto.getStatusCode());

	}

	@Test
	public void testGetBankAndBranchNameNegative() {
		logger.debug("Inside getBankAndBranchNameNegativeTest");
		when(bankRepository.findByIfscCode("SBI01122")).thenReturn(null);
		BankResponseDto bankResponseDto = bankService.getBankAndBranchName("SBI01122");
		assertEquals(204, bankResponseDto.getStatusCode());

	}
}
