package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.BankResponseDto;

public interface BankService {
	public BankResponseDto getBankAndBranchName( String ifscCode);

}
