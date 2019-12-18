package com.strickers.bankingapp.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.strickers.bankingapp.dto.FavoritePayeeDto;
import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.Customer;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.CustomerRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoritePayeeServiceTest {

	@InjectMocks
	FavoritePayeeServiceImpl favoritePayeeService;

	@Mock
	FavoritePayeeRepository favoritePayeeRepository;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	BankRepository bankRepository;

	static FavoritePayeeRequestDto favoritePayeeRequestDto = new FavoritePayeeRequestDto();
	static FavoritePayeeResponseDto favoritePayeeResponseDto = new FavoritePayeeResponseDto();
	static Customer customer = new Customer();
	static FavoritePayee favoritePayees = new FavoritePayee();
	static List<FavoritePayee> favoritePayeeList = new ArrayList<>();
	static Bank bank = new Bank();
	static FavoritePayee favoritePayee = new FavoritePayee();
	static FavoritePayee favoritePayee1 = new FavoritePayee();
	static PayeeRequestDto payeeRequestDto = new PayeeRequestDto();
	static PayeesResponseDto payeesResponseDto = new PayeesResponseDto();

	static FavoritePayeeDto favoritePayeeDto= new FavoritePayeeDto();
	
	@Before
	public void setUp() {
		customer.setCustomerId(1);
		favoritePayeeRequestDto.setAccountNumber(2356L);
		favoritePayeeRequestDto.setFavoriteName("hema");
		favoritePayeeRequestDto.setIfscCode("ifsc1");
		favoritePayee.setAccountNumber(2356L);
		favoritePayee.setPayeeId(1);
		favoritePayeeList.add(favoritePayee);
		bank.setIfscCode("ifsc1");
		favoritePayeeResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS);
		favoritePayeeResponseDto.setMessage(StringConstant.PAYEE_ADDED);
		BeanUtils.copyProperties(favoritePayee, favoritePayeeResponseDto);
		favoritePayee.setStatus(StringConstant.ACTIVE_STATUS);
		favoritePayee1.setAccountNumber(2356L);
	}

//	@Test
//	public void testAddFavoritePayee() throws MaximumFavoriteReachedException, CustomerNotExistException,
//			BankNotExistException, PayeeExistException {
//		favoritePayees = null;
//		Mockito.when(customerRepository.findByCustomerId(1)).thenReturn(customer);
//		Mockito.when(favoritePayeeRepository.findByCustomerIdAndAccountNumber(1, 2356L)).thenReturn(favoritePayees);
//		Mockito.when(favoritePayeeRepository.getPayeesByCustomerIdAndStatus(1, StringConstant.ACTIVE_STATUS))
//				.thenReturn(favoritePayeeList);
//		Mockito.when(bankRepository.findByIfscCode("ifsc1")).thenReturn(bank);
//		Mockito.when(favoritePayeeRepository.save(favoritePayee)).thenReturn(favoritePayee1);
//		favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto);
//		assertEquals(StringConstant.SUCCESS_STATUS, favoritePayeeResponseDto.getStatusCode());
//	}
	
	
	@Test(expected = CustomerNotExistException.class)
	public void testAddFavoritePayeeForCustomerNull() throws MaximumFavoriteReachedException, CustomerNotExistException,
			BankNotExistException, PayeeExistException {
		customer = null;
		favoritePayeeResponseDto = null;
		Mockito.when(customerRepository.findByCustomerId(1)).thenReturn(customer);
		favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto);
		assertNull(favoritePayeeResponseDto);
	}

//	@Test
//	public void testAddFavoritePayeeForBankNull() throws MaximumFavoriteReachedException, CustomerNotExistException,
//			BankNotExistException, PayeeExistException {
//		favoritePayees = null;
//		favoritePayeeResponseDto.setMessage(StringConstant.BANK_NOT_EXIST);
//		Mockito.when(customerRepository.findByCustomerId(1)).thenReturn(customer);
//		Mockito.when(favoritePayeeRepository.findByCustomerIdAndAccountNumber(1, 2356L)).thenReturn(favoritePayees);
//		Mockito.when(favoritePayeeRepository.getPayeesByCustomerIdAndStatus(1, StringConstant.ACTIVE_STATUS))
//				.thenReturn(favoritePayeeList);
//		Mockito.when(bankRepository.findByIfscCode("ifsc9")).thenReturn(null);
//		Mockito.when(favoritePayeeRepository.save(favoritePayee)).thenReturn(favoritePayee1);
//		String message=favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto).getMessage();
//		assertEquals(StringConstant.BANK_NOT_EXIST,message);
//	}
}
