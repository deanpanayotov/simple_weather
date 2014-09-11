package com.dpanayotov.simpleweather.general;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SimpleWeatherApplication extends Application {
	private static final String SHARED_PREFS_SETTINGS = "com.dpanayotov.simpleweather.general.SHARED_PREFS_SETTINGS";
	private static final String PREFS_KEY_UNITS = "PREFS_KEY_UNITS";
	private static final String PREFS_KEY_DATA_VALIDATION = "PREFS_KEY_DATA_VALIDATION";
	private static final String PREFS_KEY_DB_CACHE = "PREFS_KEY_DB_CACHE";
	private static final String PREFS_KEY_MISSING_DATA = "PREFS_KEY_MISSING_DATA";

	private static SimpleWeatherApplication mInstance;
	private static Gson mGson;

	private static boolean mDataValidation;
	private static boolean mDBCahce;
	private static boolean mMissingData;
	private static UNITS mUnits;
	private static SharedPreferences mPrefs;

	public enum UNITS {
		US("us"), SI("si"), CA("ca"), UK("uk");

		private final String name;

		private UNITS(String s) {
			name = s;
		}

		public String toString() {
			return name;
		}
	}

	public SimpleWeatherApplication() {
		mInstance = this;
		// mGson = new Gson();
		mGson = new GsonBuilder().setPrettyPrinting().create();
		mPrefs = getSharedPreferences(SHARED_PREFS_SETTINGS, MODE_PRIVATE);
		mUnits = UNITS.values()[mPrefs.getInt(PREFS_KEY_UNITS, 0)];
		mDataValidation = mPrefs.getBoolean(PREFS_KEY_DATA_VALIDATION, false);
		mDBCahce = mPrefs.getBoolean(PREFS_KEY_DB_CACHE, false);
		mMissingData = mPrefs.getBoolean(PREFS_KEY_MISSING_DATA, false);
	}

	public static SimpleWeatherApplication getInstance() {
		return mInstance;
	}

	public static Context getContext() {
		return mInstance.getApplicationContext();
	}

	public static Gson getGson() {
		return mGson;
	}

	public static UNITS getUnits() {
		return mUnits;
	}

	public static void setUnits(int position) {
		mUnits = UNITS.values()[position];
		mPrefs.edit().putInt(PREFS_KEY_UNITS, position).apply();
	}

	public static boolean isDataValidationEnabled() {
		return mDataValidation;
	}

	public static void setDataValidation(boolean dataValidation) {
		SimpleWeatherApplication.mDataValidation = dataValidation;
		mPrefs.edit().putBoolean(PREFS_KEY_DATA_VALIDATION, dataValidation)
				.apply();
	}

	public static boolean isDBCahceEnabled() {
		return mDBCahce;
	}

	public static void setDBCahceEnabled(boolean dBCahce) {
		SimpleWeatherApplication.mDBCahce = dBCahce;
		mPrefs.edit().putBoolean(PREFS_KEY_DB_CACHE, dBCahce).apply();

	}

	public static boolean isMissingDataEnabled() {
		return mMissingData;
	}

	public static void setMissingData(boolean missingData) {
		SimpleWeatherApplication.mMissingData = missingData;
		mPrefs.edit().putBoolean(PREFS_KEY_MISSING_DATA, missingData).apply();

	}

}
