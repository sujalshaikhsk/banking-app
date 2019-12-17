package com.strickers.bankingapp.service;

import com.strickers.bankingapp.dto.PayeeResponseDto;

public interface FavoritePayeeService {

	PayeeResponseDto getPayees(Integer customerId);

}
