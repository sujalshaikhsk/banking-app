package com.strickers.bankingapp.utils;

import java.time.LocalDate;
import java.util.Random;

/**
 * 
 * @author Sujal
 *
 */
public class Utils {

	private Utils() {}

	
	/**
	 * getCurrentDate()
	 *
	 * @return
	 */
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}


	public static String getTicketNumber() {
		// TODO Auto-generated method stub
		String ticket="BB"+generateRandom(5);
		return ticket;
	}
	
	private static final Random random = new Random();

	/**
	 * generateRandom()
	 *
	 * @param size
	 * @return
	 */
	public static int generateRandom(int size) {
		return random.nextInt(size);
	}

}
