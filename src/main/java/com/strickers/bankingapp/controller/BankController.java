package com.strickers.bankingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.strickers.bankingapp.dto.BankResponseDto;
import com.strickers.bankingapp.service.BankService;
import com.strickers.bankingapp.service.BankServiceImpl;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to get branch and bank name with ifscCode.
 *
 */
@RestController
@RequestMapping("/banks")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class BankController {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	/**
	 * The bankService.
	 */
	@Autowired
	BankService bankService;

	/**
	 * @description -> this method is used to get bank and branch name with
	 *              ifscCode.
	 * @param ifscCode
	 * @return bankResponseDto
	 */
	@GetMapping("")
	public ResponseEntity<BankResponseDto> getBankAndBranchName(@RequestParam("ifscCode") String ifscCode) {
		logger.info("Inside BankController : getBankAndBranchName");
		BankResponseDto bankResponseDto = bankService.getBankAndBranchName(ifscCode);
		return new ResponseEntity<>(bankResponseDto, HttpStatus.OK);
	}
}
