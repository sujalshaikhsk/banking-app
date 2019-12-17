package com.strickers.bankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritePayeeResponseDto {
	
	private String favoriteName;
	private String message;
	private Integer statusCode;

}
