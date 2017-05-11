package com.jda.wms.utils;

import java.util.Random;

public class Utilities {

	public static int convertStringToInteger(String stringToConvert) {
		return (int) Double.parseDouble(stringToConvert);
	}

	public static String getTenDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000000) + 1000000000);
	}

	public static String getEightDigitRAndomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000000) + 10000000);
	}
}
