package com.dpanayotov.simpleweather.util;

import android.text.format.DateFormat;

public class DateUtil {
	// TODO does this even work?
	public static final String NETWORKING_DEBUG_TIMESTAMP_FORMAT = "HH:mm:ss_SSS";
	public static final String FORECAST_LIST_FORMAT = "HH:mm";

	public static final String getFormatedDate(long time, String format) {
		return DateFormat.format(DateUtil.FORECAST_LIST_FORMAT, time)
				.toString();
	}

}
