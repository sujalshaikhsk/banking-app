package com.strickers.bankingapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.CustomerRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

@RunWith(SpringJUnit4ClassRunner.class)
public class FavoritePayeesServiceTest {

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
	
	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeServiceImpl.class);


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

	@Test
	public void testAddFavoritePayee() throws MaximumFavoriteReachedException, CustomerNotExistException,
			BankNotExistException, PayeeExistException {
		favoritePayees = null;
		Mockito.when(customerRepository.findByCustomerId(1)).thenReturn(customer);
		Mockito.when(favoritePayeeRepository.findByCustomerIdAndAccountNumber(1, 2356L)).thenReturn(favoritePayees);
		Mockito.when(favoritePayeeRepository.getPayeesByCustomerIdAndStatus(1, StringConstant.ACTIVE_STATUS))
				.thenReturn(favoritePayeeList);
		Mockito.when(bankRepository.findByIfscCode("ifsc1")).thenReturn(bank);
		Mockito.when(favoritePayeeRepository.save(favoritePayee)).thenReturn(favoritePayee1);
		favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto);
		assertEquals(StringConstant.SUCCESS_STATUS, favoritePayeeResponseDto.getStatusCode());
	}
	
	@Before
	public void setup() {
		payeeRequestDto.setAccountNumber(12345678L);
		payeeRequestDto.setFavoriteName("Sri");
		payeeRequestDto.setIfscCode("ABC1234");
		payeeRequestDto.setPayeeId(1);

		bank.setIfscCode("ABC1234");
		bank.setBankName("ABC");
		bank.setBranchName("SSS");

		customer.setCustomerId(1);
		customer.setDateOfBirth(LocalDate.of(2000, 10, 10));
		customer.setEmail("abc@gmail.com");
		customer.setFirstName("abc");
		customer.setLastName("bbb");
		customer.setMobileNumber("1234567");
		customer.setPassword("abbb");
		
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

	@Test(expected = CustomerNotExistException.class)
	public void testAddFavoritePayeeForCustomerNull() throws MaximumFavoriteReachedException, CustomerNotExistException,
			BankNotExistException, PayeeExistException {
		customer = null;
		favoritePayeeResponseDto = null;
		Mockito.when(customerRepository.findByCustomerId(1)).thenReturn(customer);
		favoritePayeeService.addFavoritePayee(1, favoritePayeeRequestDto);
		assertNull(favoritePayeeResponseDto);
	}

	
}
