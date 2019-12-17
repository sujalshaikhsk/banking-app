package com.strickers.bankingapp.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.entity.Bank;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;
import com.strickers.bankingapp.repository.BankRepository;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

@Service
public class FavoritePayeeServiceImpl implements FavoritePayeeService {

	@Autowired
	FavoritePayeeRepository favoritePayeeRepository;

	@Autowired
	BankRepository bankRepository;

	private static final Logger logger = LoggerFactory.getLogger(FavoritePayeeServiceImpl.class);

	/**
	 * @author Sri Keerthna. @since 2019-12-17
	 * @description Input is given through PayeeResponseDto which is updated in
	 *              database if the payeeId is present and give us a success message
	 * @param payeeRequestDto
	 * @return PayeeResponseDto with favoriteName,status and message
	 * @throws IfscCodeNotFoundException
	 */
	@Override
	public PayeeResponseDto updateFavoritePayee(PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException {
		PayeeResponseDto payeeResponseDto = new PayeeResponseDto();
		FavoritePayee favoritePayee = favoritePayeeRepository.findByPayeeId(payeeRequestDto.getPayeeId());
		if (favoritePayee != null) {
			logger.info("Got the payee Id and its details");
			String favoriteName = payeeRequestDto.getFavoriteName();
			Bank bank = bankRepository.findByIfscCode(payeeRequestDto.getIfscCode());
			if (bank != null) {
				logger.info("Got the bank details for that IFSC code");
				BeanUtils.copyProperties(payeeRequestDto, favoritePayee);
				favoritePayee.setBank(bank);
				favoritePayee.setUpdatedDate(LocalDate.now());
				favoritePayeeRepository.save(favoritePayee);
				payeeResponseDto.setFavoriteName(favoriteName);
				payeeResponseDto.setMessage(StringConstant.UPDATED_SUCCESS);
				payeeResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS);
				return payeeResponseDto;
			} else {
				throw new IfscCodeNotFoundException(StringConstant.IFSC_CODE_EXCEPTION);
			}
		} else {
			payeeResponseDto.setMessage(StringConstant.UPDATE_FAILED);
			payeeResponseDto.setStatusCode(StringConstant.FAILURE_STATUS);
			return payeeResponseDto;
		}

	}

}
