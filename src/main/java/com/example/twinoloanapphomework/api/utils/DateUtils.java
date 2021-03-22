package com.example.twinoloanapphomework.api.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;

public class DateUtils {

	public static Date getDateOneMonthAgo() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static long getDaysDifference(@NotNull final Date firstDate, @NotNull final Date secondDate) {
		long diffInMilliseconds = Math.abs(secondDate.getTime() - firstDate.getTime());
		return TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
	}
}
