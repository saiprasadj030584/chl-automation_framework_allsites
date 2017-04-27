package com.jda.wms.utils;

import java.util.Random;

public class Utilities {

	public static int convertStringToInteger(String stringToConvert) {
		return (int) Double.parseDouble(stringToConvert);
	}

	public static String getTenDigitRandomNumber() {
//		Random r = new Random();
//		int r1 = r.nextInt(100000000) + 999999999;
//		int r2 = r.nextInt(r.nextInt(10) + 9);
//		return (String.valueOf(r1)+String.valueOf(r2));
		
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000000) + 1000000000);
	}
}
