package com.jda.wms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

	public static String getCurrentSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static String getAddedSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 10);
		return dateFormat.format(cal.getTime());
	}
}
