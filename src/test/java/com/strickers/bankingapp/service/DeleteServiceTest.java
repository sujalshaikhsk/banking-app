package com.strickers.bankingapp.service;

import static org.junit.Assert.assertNotNull;
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

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.entity.Customer;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to do test operation for changing the
 *              status while doing delete operation.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class DeleteServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(BankServiceImplTest.class);

	@InjectMocks
	DeleteServiceImpl deleteService;

	@Mock
	FavoritePayeeRepository favoritePayeeRespository;

	ResponseDto responseDto = new ResponseDto();
	FavoritePayee favoritePayee = new FavoritePayee();
	FavoritePayee favoritePayees = new FavoritePayee();
	Customer customer = new Customer();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		responseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		favoritePayee.setPayeeId(1);
		customer.setCustomerId(1);
		responseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		responseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
		favoritePayees.setPayeeId(2);
	}

	@Test
	public void testDeleteAccount() {
		logger.debug("Inside deleteAccountTest");
		when(favoritePayeeRespository.findByPayeeIdAndCustomerId(1, 1)).thenReturn(favoritePayee);
		ResponseDto responseDto = deleteService.deleteAccount(1, 1);
		assertNotNull(responseDto);
	}

	@Test
	public void testDeleteAccountNegative() {
		logger.debug("Inside deleteAccountNegativeTest");
		when(favoritePayeeRespository.findByPayeeIdAndCustomerId(1, 1)).thenReturn(null);
		ResponseDto responseDto = deleteService.deleteAccount(1, 1);
		assertEquals(204, responseDto.getStatusCode());
	}

}
