package com.strickers.bankingapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayeeResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	List<FavoritePayeeDto> favoritePayeeDtos;

	String message;
	Integer statusCode;
}
