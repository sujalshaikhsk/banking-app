package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;

public interface FavoritePayeeService {

	public PayeesResponseDto updateFavoritePayee(PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException;

	PayeeResponseDto getPayees(Integer customerId);

}
