package com.strickers.bankingapp.exception;

public class BankNotExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public BankNotExistException(String exception) {
		super(exception);
	}


}
