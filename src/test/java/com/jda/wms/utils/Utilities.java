package com.jda.wms.utils;

import java.util.Random;

public class Utilities {

	public static int convertStringToInteger(String stringToConvert) {
		return (int) Double.parseDouble(stringToConvert);
	}

	public static int getTenDigitRandomNumber() {
		Random r = new Random();
		return r.nextInt(r.nextInt(100000000) + 999999999);
	}
}
