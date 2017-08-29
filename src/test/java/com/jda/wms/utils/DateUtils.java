package com.jda.wms.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String getCurrentSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String getCurrentSystemDateInDBFormat() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime()).toUpperCase();
	}

	public static String getAddedSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 10);
		return dateFormat.format(cal.getTime());
	}

	public static String getAddedSystemYear() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		return dateFormat.format(cal.getTime());
	}

	public static String getPrevSystemYear() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -2);
		return dateFormat.format(cal.getTime());
	}

	public static String getSecond() {
		SimpleDateFormat sdf = new SimpleDateFormat("ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getMin() {
		SimpleDateFormat sdf = new SimpleDateFormat("mm");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getHour() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static String getConvertDate(String expDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(expDate);
		Calendar cal = Calendar.getInstance();
		return dateFormat.toString();
	}

	public static String getCurrentSystemTimeLessThan2Minutes() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("kk:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -2);
		return dateFormat.format(cal.getTime());
	}
}
