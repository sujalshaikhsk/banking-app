package com.strickers.bankingapp.exception;

public class CustomerNotExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CustomerNotExistException(String exception) {
		super(exception);
	}


}
