package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;
import com.strickers.bankingapp.service.FavoritePayeeService;

@RestController
@RequestMapping("/favoritepayees")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class FavoritePayeeController {

	@Autowired
	FavoritePayeeService favoritePayeeService;

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeController.class);

	/**
	 * @description -> this method is used to add the favorite Payee to the
	 *              particular customerId
	 * @param customerId
	 * @param favoritePayeeRequestDto
	 * @return FavoritePayeeResponseDto
	 * @throws MaximumFavoriteReachedException
	 * @throws CustomerNotExistException
	 * @throws BankNotExistException
	 * @throws PayeeExistException
	 */
	@PostMapping("/{customerId}")
	public ResponseEntity<FavoritePayeeResponseDto> addFavoritePayee(@PathVariable("customerId") Integer customerId,
			@RequestBody FavoritePayeeRequestDto favoritePayeeRequestDto) throws MaximumFavoriteReachedException,
			CustomerNotExistException, BankNotExistException, PayeeExistException {
		logger.info("Adding Favorite Payee");
		FavoritePayeeResponseDto favoritePayeeResponseDto = favoritePayeeService.addFavoritePayee(customerId,
				favoritePayeeRequestDto);
		if (favoritePayeeResponseDto != null) {
			return new ResponseEntity<>(favoritePayeeResponseDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(favoritePayeeResponseDto, HttpStatus.NO_CONTENT);
		}
	}

}
