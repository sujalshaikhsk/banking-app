package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.service.BankServiceImpl;
import com.strickers.bankingapp.service.DeleteService;

/**
 * @author Vasavi
 * @since  2019-12-17
 * @description -> this class is used to change the status of the particular payeeId 
 *
 */
@RestController
@RequestMapping("/{customerId}/payees")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class DeleteController {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	/**
	 * The deleteService.
	 */
	@Autowired
	DeleteService deleteService;

	/**
	 * @description -> this method is used to change the status of the particular payeeId
	 * @param payeeId
	 * @return responseDto
	 */
	@DeleteMapping("")
	public ResponseEntity<ResponseDto> deleteAccount(@PathVariable("customerId") Integer customerId, @RequestParam("payeeId") Integer payeeId) {
		logger.info("Inside DeleteController :deleteAccount");
		ResponseDto responseDto = deleteService.deleteAccount(customerId, payeeId);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}
