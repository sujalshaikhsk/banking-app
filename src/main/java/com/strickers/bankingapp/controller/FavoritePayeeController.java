package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.service.FavoritePayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("{customerId}/payees")
public class FavoritePayeeController {
	
	@Autowired
	private FavoritePayeeService favoritePayeeService;

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeController.class);

	/**
	 * @author Sujal
	 * @description This api is used fetch Payees details based on customer
	 * @param customerId is login user id
	 * @return PayeeResponseDto is the list of Favorite Payees and response code
	 */
	@GetMapping("")
	public ResponseEntity<PayeeResponseDto> getPayees(@PathVariable("customerId") Integer customerId) {
		PayeeResponseDto payeeResponseDto = favoritePayeeService.getPayees(customerId);
		if (payeeResponseDto != null) {
			logger.info("payees result found");
			return new ResponseEntity<>(payeeResponseDto, HttpStatus.OK);
		} else {
			logger.error("payees result not found");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
