package com.strickers.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.service.FavoritePayeeService;
import com.strickers.bankingapp.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoritePayeeControllerTest {

	@InjectMocks
	FavoritePayeeController favoritePayeeController;

	@Mock
	FavoritePayeeService favoritePayeeService;

	static PayeeRequestDto payeeRequestDto = new PayeeRequestDto();
	static PayeesResponseDto payeesResponseDto = new PayeesResponseDto();

	FavoritePayeeResponseDto favoritePayeeResponseDto = new FavoritePayeeResponseDto();
	FavoritePayeeRequestDto favoritePayeeRequestDto = new FavoritePayeeRequestDto();
	
	static PayeeResponseDto payeeResponseDto=new PayeeResponseDto();

	@Before
	public void setup() {
		payeeRequestDto.setAccountNumber(12345678L);
		payeeRequestDto.setFavoriteName("Sri");
		payeeRequestDto.setIfscCode("ABC1234");
		payeeRequestDto.setPayeeId(1);

		payeesResponseDto.setFavoriteName("Sri");
		payeesResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
		payeesResponseDto.setStatusCode(200);

		favoritePayeeRequestDto.setAccountNumber(12234L);
		favoritePayeeRequestDto.setFavoriteName("sjgd");
		favoritePayeeRequestDto.setIfscCode("ifsc2");
		favoritePayeeResponseDto.setStatusCode(200);
	}

	@Test
	public void testAddFavoritePayeePositive() throws MaximumFavoriteReachedException, CustomerNotExistException,
			BankNotExistException, PayeeExistException {
		Mockito.when(favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto))
				.thenReturn(favoritePayeeResponseDto);
		Integer result = favoritePayeeController.addFavoritePayee(1, favoritePayeeRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void testAddFavoritePayeeNegative() throws MaximumFavoriteReachedException, CustomerNotExistException,
			BankNotExistException, PayeeExistException {
		FavoritePayeeResponseDto favoritePayeeResponseDto = null;
		Mockito.when(favoritePayeeService.addFavoritePayee(3, favoritePayeeRequestDto))
				.thenReturn(favoritePayeeResponseDto);
		Integer result = favoritePayeeController.addFavoritePayee(3, favoritePayeeRequestDto).getStatusCodeValue();
		assertEquals(204, result);
	}

	@Test
	public void updateFavoritePayeePositive() throws IfscCodeNotFoundException {
		Integer customerId = 1;
		Mockito.when(favoritePayeeService.updateFavoritePayee(payeeRequestDto)).thenReturn(payeesResponseDto);
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

	@Test
	public void getPayeesPositive() {
		Integer customerId = 1;
		Mockito.when(favoritePayeeService.getPayees(customerId)).thenReturn(payeeResponseDto);
		Integer result = favoritePayeeController.getPayees(customerId).getStatusCodeValue();
		assertEquals(200, result);
	}

	@Test
	public void getPayeesNegative() {
		Integer customerId = 1;
		Mockito.when(favoritePayeeService.getPayees(customerId)).thenReturn(null);
		Integer result = favoritePayeeController.getPayees(customerId).getStatusCodeValue();
		assertEquals(400, result);
	}

}
