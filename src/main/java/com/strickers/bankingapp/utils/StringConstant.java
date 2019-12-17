package com.strickers.bankingapp.utils;

/**
 * 
 * @author Sujal
 *
 */
public class StringConstant {

	private StringConstant() {
	}

	public static final String ACTIVE_STATUS = "ACTIVE";
	public static final String DEACTIVE_STATUS = "DEACTIVE";
	public static final String PAYEE_ADDED = "Your favorite Payee is added Successfully";
	public static final String PAYEE_NOT_ADDED = "Your favorite Payee is not added";
	public static final String CUSTOMER_NOT_EXIST = "Customer does not exist";
	public static final String BANK_NOT_EXIST = "Bank does not exist";
	public static final String MAX_FAVORITE_REACHED = "You already added 10 favorite paylist";
	public static final Integer SUCCESS_STATUS = 200;
	public static final Integer FAILURE_STATUS = 400;
	public static final int MAX_FAVORITE_PAYEES = 10;
	public static final String PAYEE_ALREADY_EXIST = "You already added this account into your favorite payee list";
	
	public static final String SUCCESS = "Success";
	public static final Integer SUCCESS_STATUS_CODE = 200;
	public static final String FAILURE = "Failure";
	public static final Integer FAILURE_STATUS_CODE = 204;

	
	public static final String UPDATED_SUCCESS="Updated Successfully";
	public static final String IFSC_CODE_EXCEPTION="Invalid IFSC Code";
	public static final String UPDATE_FAILED="Not Updated";
}
