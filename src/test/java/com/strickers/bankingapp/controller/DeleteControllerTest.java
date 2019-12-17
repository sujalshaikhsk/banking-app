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

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.service.DeleteService;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to do test operation for changing the
 *              status while deleting the record.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DeleteControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(BankControllerTest.class);
	@InjectMocks
	DeleteController deleteController;
	@Mock
	DeleteService deleteService;
	ResponseDto responseDto = new ResponseDto();
	FavoritePayee favoritePayee = new FavoritePayee();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		responseDto.setStatusCode(200);
		favoritePayee.setPayeeId(1);
	}

	@Test
	public void testDeleteAccount() {
		logger.debug("Inside deleteControllerTest");
		when(deleteService.deleteAccount(1)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> result = deleteController.deleteAccount(1);
		assertEquals(200, result.getBody().getStatusCode());
	}

}
