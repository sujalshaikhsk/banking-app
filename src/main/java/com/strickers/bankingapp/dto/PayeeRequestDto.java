package com.strickers.bankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayeeRequestDto {

	private Integer payeeId;
	private Long accountNumber;
	private String favoriteName;
	private String ifscCode;
}
