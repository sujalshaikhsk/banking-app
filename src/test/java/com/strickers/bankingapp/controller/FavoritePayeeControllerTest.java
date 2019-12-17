package com.strickers.bankingapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.service.FavoritePayeeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FavoritePayeeControllerTest {
	
	@InjectMocks
	FavoritePayeeController favoritePayeeController;
	
	@Mock
	FavoritePayeeService favoritePayeeService;
	
	FavoritePayeeResponseDto favoritePayeeResponseDto=new FavoritePayeeResponseDto();
	FavoritePayeeRequestDto favoritePayeeRequestDto=new FavoritePayeeRequestDto();
	
	@Before
	public void setUp() {
		favoritePayeeRequestDto.setAccountNumber(12234L);
		favoritePayeeRequestDto.setFavoriteName("sjgd");
		favoritePayeeRequestDto.setIfscCode("ifsc2");
		favoritePayeeResponseDto.setStatusCode(200);
	}
	
	@Test
	public void testAddFavoritePayeePositive() throws MaximumFavoriteReachedException, CustomerNotExistException, BankNotExistException, PayeeExistException {
		Mockito.when(favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto)).thenReturn(favoritePayeeResponseDto);
		Integer result=favoritePayeeController.addFavoritePayee(1, favoritePayeeRequestDto).getStatusCodeValue();
		assertEquals(200, result);
	}
	
	@Test
	public void testAddFavoritePayeeNegative() throws MaximumFavoriteReachedException, CustomerNotExistException, BankNotExistException, PayeeExistException {
		FavoritePayeeResponseDto favoritePayeeResponseDto=null;
		Mockito.when(favoritePayeeService.addFavoritePayee(3, favoritePayeeRequestDto)).thenReturn(favoritePayeeResponseDto);
		Integer result=favoritePayeeController.addFavoritePayee(3, favoritePayeeRequestDto).getStatusCodeValue();
		assertEquals(204, result);
	}

}
