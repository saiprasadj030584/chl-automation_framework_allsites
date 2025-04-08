package com.jda.wms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Utilities {

	public static int convertStringToInteger(String stringToConvert) {
		return (int) Double.parseDouble(stringToConvert);
	}

	public static String getTenDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(9000000) + 1000000000);
	}
	public static String getNineDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(90000000) + 100000000);
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

	public static String getThreeDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900) + 100);
	}
	public static String getSixDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900000) + 100000);
	}
	public static String getsevenDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(900000) + 1000000);
	}
	public static String getTwoDigitRandomNumber() {
		Random r = new Random(System.currentTimeMillis());
		return String.valueOf(r.nextInt(90) + 10);
	}
	public static String getAddedSystemYear() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		return dateFormat.format(cal.getTime());
	} 

	public static String getFourteenDigitRandomNumber() {
        return (getFiveDigitRandomNumber()+getNineDigitRandomNumber());
    }
	public static String getFourteenDigit_975Number() {
        return (getTwoDigitRandomNumber()+getNineDigitRandomNumber()+975);
    }
	
	public static String getP_EightNumber() {
		return ("P"+getsevenDigitRandomNumber());
	}

}
