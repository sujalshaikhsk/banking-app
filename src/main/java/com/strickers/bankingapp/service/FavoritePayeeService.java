package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.FavoritePayeeRequestDto;
import com.strickers.bankingapp.dto.FavoritePayeeResponseDto;
import com.strickers.bankingapp.exception.BankNotExistException;
import com.strickers.bankingapp.exception.CustomerNotExistException;
import com.strickers.bankingapp.exception.MaximumFavoriteReachedException;
import com.strickers.bankingapp.exception.PayeeExistException;

public interface FavoritePayeeService {
	
	public FavoritePayeeResponseDto addFavoritePayee(Integer customerId,FavoritePayeeRequestDto favoritePayeeRequestDto) throws MaximumFavoriteReachedException, CustomerNotExistException, BankNotExistException, PayeeExistException;

}
