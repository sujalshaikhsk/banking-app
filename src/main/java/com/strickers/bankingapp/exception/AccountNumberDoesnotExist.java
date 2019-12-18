package com.strickers.bankingapp.exception;

public class AccountNumberDoesnotExist extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public AccountNumberDoesnotExist(String exception) {
		super(exception);
	}

}
