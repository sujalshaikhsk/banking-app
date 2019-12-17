package com.strickers.bankingapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoritePayeeServiceTest {

	@InjectMocks
	FavoritePayeeServiceImpl favoritePayeeService;

	@Mock
	FavoritePayeeRepository favoritePayeeRepository;

	@Mock
	BankRepository bankRepository;

	static PayeeRequestDto payeeRequestDto = new PayeeRequestDto();
	static PayeesResponseDto payeesResponseDto = new PayeesResponseDto();
	static Bank bank = new Bank();
	static FavoritePayee favoritePayee = new FavoritePayee();

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeServiceImpl.class);

	@Before
	public void setup() {
		payeeRequestDto.setAccountNumber(12345678L);
		payeeRequestDto.setFavoriteName("Sri");
		payeeRequestDto.setIfscCode("ABC1234");
		payeeRequestDto.setPayeeId(1);

		bank.setIfscCode("ABC1234");

		favoritePayee.setAccountNumber(12345678L);
		favoritePayee.setFavoriteName("Divya");
		favoritePayee.setPayeeId(1);
		favoritePayee.setBank(bank);
		favoritePayee.setUpdatedDate(LocalDate.now());

	}

	@Test
	public void updateFavoritePayeePositive() throws IfscCodeNotFoundException {
		Mockito.when(favoritePayeeRepository.findByPayeeId(payeeRequestDto.getPayeeId())).thenReturn(favoritePayee);
		logger.info("Got the payee Id and its details");
		Mockito.when(bankRepository.findByIfscCode(payeeRequestDto.getIfscCode())).thenReturn(bank);
		logger.info("Got the bank details for that IFSC code");
		BeanUtils.copyProperties(payeeRequestDto, favoritePayee);
		payeesResponseDto.setFavoriteName("Sri");
		payeesResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
		payeesResponseDto.setStatusCode(200);
		PayeesResponseDto payeesResponseDto = favoritePayeeService.updateFavoritePayee(payeeRequestDto);
		assertNotNull(payeesResponseDto);
	}

	@Test
	public void updateFavoritePayeeNegative() throws IfscCodeNotFoundException {
		Mockito.when(favoritePayeeRepository.findByPayeeId(payeeRequestDto.getPayeeId())).thenReturn(null);
		payeesResponseDto.setMessage(StringConstant.UPDATE_FAILED);
		payeesResponseDto.setStatusCode(StringConstant.FAILURE_STATUS);
		String payeesResponseDto = favoritePayeeService.updateFavoritePayee(payeeRequestDto).getMessage();
		assertEquals(StringConstant.UPDATE_FAILED, payeesResponseDto);
	}

	@Test(expected = IfscCodeNotFoundException.class)
	public void updateFavoritePayeeNegativeNull() throws IfscCodeNotFoundException {
		Mockito.when(favoritePayeeRepository.findByPayeeId(payeeRequestDto.getPayeeId())).thenReturn(favoritePayee);
		logger.info("Got the payee Id and its details");
		Mockito.when(bankRepository.findByIfscCode(payeeRequestDto.getIfscCode())).thenReturn(null);
		String payeesResponseDto = favoritePayeeService.updateFavoritePayee(payeeRequestDto).getMessage();
		assertEquals(StringConstant.IFSC_CODE_EXCEPTION, payeesResponseDto);
	}
}
