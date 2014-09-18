package com.dpanayotov.simpleweather.util;

import android.util.Log;

public class LogUtil {
	public static final boolean DEBUG_ENABLED = true;
	public static final boolean JSON_PRETTY_PRINT_ENABLED = true;
	private static final String DEFAULT_TAG = "SW-DEFAULT";
	private static final String DEBUG_TAG = "SW-DEBUG";
	public static final String CACHE_TAG = "SW-CACHE";

	public static final boolean NETWORKING_DEBUG_ENABLED = true;
	private static final String NETWORKING_TAG = "SW-NETWORKING";

	public static void d(String message) {
		d(DEBUG_TAG, message);
	}

	public static void e(String message) {
		e(DEFAULT_TAG, message);
	}

	public static void i(String message) {
		i(DEFAULT_TAG, message);
	}

	public static void v(String message) {
		v(DEFAULT_TAG, message);
	}

	public static void w(String message) {
		w(DEFAULT_TAG, message);
	}

	public static void wtf(String message) {
		wtf(DEFAULT_TAG, message);
	}

	/**
	 * "n" stands for "networking
	 */
	public static void n(String message) {
		if (NETWORKING_DEBUG_ENABLED) {
			Log.d(NETWORKING_TAG, message);
		}
	}

	public static void d(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.d(tag, message);
		}
	}

	public static void e(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.e(tag, message);
		}
	}

	public static void i(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.i(tag, message);
		}
	}

	public static void v(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.v(tag, message);
		}
	}

	public static void w(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.w(tag, message);
		}
	}

	public static void wtf(String tag, String message) {
		if (DEBUG_ENABLED) {
			Log.wtf(tag, message);
		}
	}
}
