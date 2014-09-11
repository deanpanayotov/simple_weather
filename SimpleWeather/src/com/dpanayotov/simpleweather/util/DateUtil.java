package com.dpanayotov.simpleweather.util;

import android.text.format.DateFormat;

public class DateUtil {

	public static final long MILLISECOND = 1;
	public static final long SECOND = 1000 * MILLISECOND;
	public static final long MINUTE = 60 * SECOND;
	public static final long HOUR = 60 * MINUTE;
	public static final long DAY = 24 * HOUR;
	public static final long WEEK = 7 * DAY;

	// TODO fix milliseconds
	public static final String NETWORKING_DEBUG_TIMESTAMP_FORMAT = "HH:mm:ss";
	public static final String FORECAST_SINGLE_ITEM_FORMAT_HOURLY = "EEE dd.MM HH:mm";
	public static final String FORECAST_SINGLE_ITEM_FORMAT_daily = "EEEE, dd.MM";
	public static final String FORECAST_LIST_FORMAT_HOURLY = "HH:mm";
	public static final String FORECAST_LIST_FORMAT_DAILY = "dd.MM";
	public static final String FORECAST_LIST_MIDNIGHT = "00:00";
	
	/**
	 * @return a locale specific formatted date string
	 */
	public static final String getFormatedDate(long time, String format) {
		return DateFormat.format(format, time).toString();
	}

	public static final long convertGMTTimeToLocalTimezone(long time, int offset) {
		return time + HOUR * offset;
	}

}
