package com.hduo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
	public static final String NEW = "new";
	public static final String NEW_UPDATED = "new_updated";
	public static final String UPDATED = "updated";
	public static final String DELETED = "deleted";

	public static Date stringToDate(String dateString) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = (Date) df.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String dateToString(Date date) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = null;
			dateString = df.format(date);
			return dateString;
	}
	
	public static String dateOneMonthAgo(Date to){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(to);
		calendar.add(Calendar.MONDAY,-1);
		Date from = calendar.getTime();
		return dateToString(from);
	}
	
}
