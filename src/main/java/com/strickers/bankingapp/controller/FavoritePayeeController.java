package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.service.FavoritePayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("{customerId}/payees")
public class FavoritePayeeController {

	@Autowired
	FavoritePayeeService favoritePayeeService;

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeController.class);

	/**
	 * @author Sri Keerthna. @since 2019-12-17
	 * @description Input is given through PayeeResponseDto which is updated in
	 *              database if the payeeId is present and give us a success message
	 * @param payeeRequestDto
	 * @return PayeeResponseDto with favoriteName,status and message
	 * @throws IfscCodeNotFoundException
	 */
	@PutMapping
	ResponseEntity<PayeesResponseDto> updateFavoritePayee(@PathVariable("customerId") Integer customerId,
			@RequestBody PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException {
		PayeesResponseDto responseDto = favoritePayeeService.updateFavoritePayee(payeeRequestDto);
		if (responseDto != null) {
			logger.info("Updated successfully");
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} else {
			logger.info("Invalid IFSC Code");
			return new ResponseEntity<>(responseDto, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	/**
	 * @author Sujal
	 * @description This api is used fetch Payees details based on customer
	 * @param searchKey is used to search the above mentioned field of profile
	 * @return PayeeResponseDto is the list of Favorite Payees and response code
	 */
	@GetMapping
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
