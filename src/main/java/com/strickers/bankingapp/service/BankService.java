package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.BankResponseDto;

/**
 * @author Vasavi
 * @since 2019-12-17
 *
 */
public interface BankService {
	/**
	 * @description -> this method is used to get bank and branch name with
	 *              ifscCode.
	 * @param ifscCode
	 * @return bankResponseDto
	 */
	public BankResponseDto getBankAndBranchName( String ifscCode);

}
