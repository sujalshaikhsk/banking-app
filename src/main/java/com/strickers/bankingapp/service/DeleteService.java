package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.ResponseDto;

/**
 * @author Vasavi
 * @since 2019-12-17
 *
 */
public interface DeleteService {
	/**
	 * @description ->this class is used to change the status of the particular
	 *              payeeId when we are doing delete operation.
	 * @param payeeId
	 * @param payeeId2 
	 * @return responseDto
	 */
	public ResponseDto deleteAccount(Integer customerId, Integer payeeId);

}
