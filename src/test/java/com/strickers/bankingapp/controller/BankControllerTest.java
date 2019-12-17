package com.strickers.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.bankingapp.dto.BankResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.service.BankService;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to do test operation for getting bank and
 *              branch name with ifscCode.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BankControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(BankControllerTest.class);
	@InjectMocks
	BankController bankController;
	@Mock
	BankService bankService;
	BankResponseDto bankResponseDto = new BankResponseDto();
	Bank bank = new Bank();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		bank.setIfscCode("SBI01122");
		bankResponseDto.setStatusCode(200);
	}

	@Test
	public void testGetBankAndBranchName() {
		logger.debug("Inside getBankAndBranchNameTest method");
		when(bankService.getBankAndBranchName("SBI01122")).thenReturn(bankResponseDto);
		ResponseEntity<BankResponseDto> result = bankController.getBankAndBranchName("SBI01122");
		assertEquals(200, result.getBody().getStatusCode());
	}

}
