package com.strickers.bankingapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayeeResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<FavoritePayeeDto> favoritePayees;

	String message;
	Integer statusCode;
}
