package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.service.BankServiceImpl;
import com.strickers.bankingapp.service.DeleteService;

@RestController
@RequestMapping("/{customerId}/payees")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class DeleteController {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	DeleteService deleteService;

	@DeleteMapping("")
	public ResponseEntity<ResponseDto> deleteAccount(@RequestParam("payeeId") Integer payeeId) {
		logger.info("Inside DeleteController :deleteAccount");
		ResponseDto responseDto = deleteService.deleteAccount(payeeId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}
