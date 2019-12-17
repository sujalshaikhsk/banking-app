package com.strickers.bankingapp.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BankResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bankName;
	private String branchName;
	private String message;
	private Integer statusCode;

}
