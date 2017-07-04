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

	public static String getEightDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000000) + 10000000);
	}

	public static String getFiveDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000) + 10000);
	}

	public static double getRoundOffToTwoDecimal(double a) {
		return Math.round(a * 100.0) / 100.0;
	}

	public static String getFourDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000) + 1000);
	}
}
