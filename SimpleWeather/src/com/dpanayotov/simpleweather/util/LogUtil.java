package com.dpanayotov.simpleweather.util;

import android.util.Log;

public class LogUtil {
	private static final boolean DEBUG_ENABLED = true;
	private static final String DEFAULT_TAG = "TEST";

	public static void d(String message) {
		d(DEFAULT_TAG, message);
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
