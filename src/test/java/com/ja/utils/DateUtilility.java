package com.ja.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilility {

	public static String getCurrentDateFormatted() {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		return dateFormat.format(date);

	}

}
