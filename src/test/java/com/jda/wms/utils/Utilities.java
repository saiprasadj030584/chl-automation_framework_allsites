package com.jda.wms.utils;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

	public static String getSixDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900000) + 100000);
	}

	public static String getFiveDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900) + 10000);
	}

	public static String getTwoDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(90) + 10);
	}

	public static String getOneDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9) + 1);
	}

	public static double getRoundOffToTwoDecimal(double a) {
		return Math.round(a * 100.0) / 100.0;
	}

	public static String getFourDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000) + 1000);
	}

	public static String getThreeDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900) + 100);
	}

	public static int randIntBetween2Values(int bound1, int bound2) {
		int min = (int) Math.min(bound1, bound2);
		int max = (int) Math.max(bound1, bound2);
		return (int) (min + (Math.random() * (max - min)));
	}
	
	public static String getSevenDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900000) + 1000000);
	}
	
	public static String get32DigitRandomNumber(){
		long value1, value2, value3, max = 999999999;
		boolean mainTable = true, interfaceTable = true;
		String tempValue;
			value1 = ThreadLocalRandom.current().nextLong(100000000, max);
			value2 = ThreadLocalRandom.current().nextLong(100000000, max);
			value3 = ThreadLocalRandom.current().nextLong(100000000, max);
			int tempInt = ThreadLocalRandom.current().nextInt(10000, 99999);
			tempValue = String.valueOf(value1) + String.valueOf(value2) + String.valueOf(value3)
					+ String.valueOf(tempInt);
		return tempValue;
	}
	public boolean validateNull(String arguments){
		if(arguments==null)
			return false;
		else
			return true;
	}
}
