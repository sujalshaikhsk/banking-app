package com.strickers.bankingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.BankResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description -> this class is used to get bank name and branch name with
 *              ifscCode
 *
 */
@Service
public class BankServiceImpl implements BankService {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	/**
	 * The bankRepository
	 */
	@Autowired
	BankRepository bankRepository;

	/**
	 * @description -> this method is used to get bank and branch name with
	 *              ifscCode.
	 * @param ifscCode
	 * @return bankResponseDto
	 */
	@Override
	public BankResponseDto getBankAndBranchName(String ifscCode) {
		BankResponseDto bankResponseDto = null;
		logger.info("Inside getBankAndBranchName method ");
		Bank bank = bankRepository.findByIfscCode(ifscCode);
		if (bank != null) {
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
