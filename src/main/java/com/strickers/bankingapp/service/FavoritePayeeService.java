package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;

public interface FavoritePayeeService {

	public PayeeResponseDto updateFavoritePayee(PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException;
}
