package com.example.twinoloanapphomework.api.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date getDateOneMonthAgo() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}
}
