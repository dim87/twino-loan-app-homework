package com.example.twinoloanapphomework.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestDateUtils {

	private static final String BACKEND_DATE_FORMAT = "yyyy-MM-dd";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(BACKEND_DATE_FORMAT);

	public static String formatDate(final Date date) {
		return DATE_FORMATTER.format(date.toInstant().atZone(ZoneId.systemDefault()));
	}
}
