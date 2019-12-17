package com.strickers.bankingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.repository.FavoritePayeeRespository;
import com.strickers.bankingapp.utils.StringConstant;

@Service
public class DeleteServiceImpl implements DeleteService {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	FavoritePayeeRespository favoritePayeeRespository;

	@Override
	public ResponseDto deleteAccount(Integer payeeId) {
		ResponseDto responseDto = null;
		logger.info("Inside deleteAccount method");
		FavoritePayee favoritePayee = favoritePayeeRespository.findByPayeeId(payeeId);
		if (favoritePayee != null) {
			responseDto = new ResponseDto();
			favoritePayee.setStatus(StringConstant.DEACTIVE_STATUS);
			FavoritePayee favoritePayee2 = favoritePayeeRespository.save(favoritePayee);
			if (favoritePayee2 != null) {
				responseDto.setMessage(StringConstant.SUCCESS);
				responseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
			}
		} else {
			responseDto = new ResponseDto();
			responseDto.setMessage(StringConstant.FAILURE);
			responseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);

		}

		return responseDto;
	}

}
