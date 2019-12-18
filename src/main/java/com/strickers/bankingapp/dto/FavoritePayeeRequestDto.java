package com.strickers.bankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritePayeeRequestDto {
	private Long accountNumber;
	private String favoriteName;
	private String ifscCode;
}
