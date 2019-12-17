package com.strickers.bankingapp.exception;

public class PayeeExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public PayeeExistException(String exception) {
		super(exception);
	}


}
