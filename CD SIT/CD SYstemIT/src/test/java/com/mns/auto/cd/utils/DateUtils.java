package com.mns.auto.cd.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class DateUtils {

	public static String getCurrentSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String getDateInddMMMyyyyFormat() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String getAddedSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 10);
		return dateFormat.format(cal.getTime());
	} 
	
	public static String getTCLogTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static int getCurrSysTime() {
		DateFormat dateFormat = new SimpleDateFormat("mm");
		Calendar cal = Calendar.getInstance();
		System.out.println("Time in Minutes : " + dateFormat.format(cal.getTime()));
		int minuteNow = Integer.parseInt(dateFormat.format(cal.getTime()));
		return minuteNow;
	}
}
