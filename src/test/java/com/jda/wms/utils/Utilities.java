package com.jda.wms.utils;

import java.util.Random;

public class Utilities {

	public int convertStringToInteger(String stringToConvert) {
		return (int) Double.parseDouble(stringToConvert);
	}

	public int getTenDigitRandomNumber() {
		Random r = new Random();
		// int ran1 = (int) Math.round(Math.random()*1000000000);
		// System.out.println(ran1);
		// int ran2 = (int) Math.round(Math.random()*10);
		// System.out.println(ran2);
		// String uniqueID = String.valueOf(ran1)+String.valueOf(ran2);
		return r.nextInt(100000000) + 999999;
		// return uniqueID;
	}
}
