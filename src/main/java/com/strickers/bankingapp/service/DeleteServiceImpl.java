package com.strickers.bankingapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.strickers.bankingapp.dto.ResponseDto;
import com.strickers.bankingapp.entity.FavoritePayee;
import com.strickers.bankingapp.repository.FavoritePayeeRepository;
import com.strickers.bankingapp.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-17
 * @description ->this class is used to change the status of the particular
 *              payeeId when we are doing delete operation.
 *
 */
@Service
public class DeleteServiceImpl implements DeleteService {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	FavoritePayeeRepository favoritePayeeRepository;
	/**
	 * @description ->this class is used to change the status of the particular
	 *              payeeId when we are doing delete operation.
	 * @param payeeId
	 * @return responseDto
	 */
	@Override
	public ResponseDto deleteAccount(Integer customerId, Integer payeeId) {
		ResponseDto responseDto = null;
		logger.info("Inside deleteAccount method");
		FavoritePayee favoritePayee = favoritePayeeRepository.findByPayeeIdAndCustomerId(customerId, payeeId);
		if (favoritePayee != null) {
			responseDto = new ResponseDto();
			favoritePayee.setStatus(StringConstant.DEACTIVE_STATUS);
			FavoritePayee favoritePayee2 = favoritePayeeRepository.save(favoritePayee);
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
