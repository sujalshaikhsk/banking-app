package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.dto.PayeeRequestDto;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;

import com.strickers.bankingapp.dto.PayeeResponseDto;
import com.strickers.bankingapp.dto.PayeesResponseDto;
import com.strickers.bankingapp.exception.IfscCodeNotFoundException;

public interface FavoritePayeeService {
	
	public FavoritePayeeResponseDto addFavoritePayee(Integer customerId,FavoritePayeeRequestDto favoritePayeeRequestDto) throws MaximumFavoriteReachedException, CustomerNotExistException, BankNotExistException, PayeeExistException;

	public PayeesResponseDto updateFavoritePayee(PayeeRequestDto payeeRequestDto) throws IfscCodeNotFoundException;

	PayeeResponseDto getPayees(Integer customerId);

}
