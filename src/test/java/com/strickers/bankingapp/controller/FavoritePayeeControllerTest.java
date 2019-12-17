package com.strickers.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.service.FavoritePayeeService;
import com.strickers.bankingapp.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoritePayeeControllerTest {

	@InjectMocks
	FavoritePayeeController favoritePayeeController;

	@Mock
	FavoritePayeeService favoritePayeeService;

	static PayeeRequestDto payeeRequestDto = new PayeeRequestDto();
	static PayeeResponseDto payeeResponseDto = new PayeeResponseDto();
	static Bank bank = new Bank();
	static FavoritePayee favoritePayee = new FavoritePayee();

	@Before
	public void setup() {
		payeeRequestDto.setAccountNumber(12345678L);
		payeeRequestDto.setFavoriteName("Sri");
		payeeRequestDto.setIfscCode("ABC1234");
		payeeRequestDto.setPayeeId(1);

		payeeResponseDto.setFavoriteName("Sri");
		payeeResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
		payeeResponseDto.setStatusCode(200);
	}

	@Test
	public void updateFavoritePayeePositive() throws IfscCodeNotFoundException {
		Integer customerId = 1;
		Mockito.when(favoritePayeeService.updateFavoritePayee(payeeRequestDto)).thenReturn(payeeResponseDto);
		Integer result = favoritePayeeController.updateFavoritePayee(customerId, payeeRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void updateFavoritePayeeNegative() throws IfscCodeNotFoundException {
		Integer customerId = 1;
		Mockito.when(favoritePayeeService.updateFavoritePayee(payeeRequestDto)).thenReturn(null);
		Integer result = favoritePayeeController.updateFavoritePayee(customerId, payeeRequestDto).getStatusCodeValue();
		assertEquals(406, result);
	}

}
