package com.strickers.bankingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.BankResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.utils.StringConstant;

@Service
public class BankServiceImpl implements BankService {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	BankRepository bankRepository;

	@Override
	public BankResponseDto getBankAndBranchName(String ifscCode) {
		BankResponseDto bankResponseDto = null;
		logger.info("Inside getBankAndBranchName method ");
		Bank bank = bankRepository.findByIfscCode(ifscCode);
		if (ifscCode != null) {
			bankResponseDto = new BankResponseDto();
			bankResponseDto.setBankName(bank.getBankName());
			bankResponseDto.setBranchName(bank.getBranchName());
			bankResponseDto.setMessage(StringConstant.SUCCESS);
			bankResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		} else {
			bankResponseDto = new BankResponseDto();
			bankResponseDto.setMessage(StringConstant.FAILURE);
			bankResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
		}

		return bankResponseDto;
	}

}
