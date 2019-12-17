package com.strickers.bankingapp.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		
		payeeResponseDto.setFavoriteName("Sri");
		payeeResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
		payeeResponseDto.setStatusCode(200);
	}
}
