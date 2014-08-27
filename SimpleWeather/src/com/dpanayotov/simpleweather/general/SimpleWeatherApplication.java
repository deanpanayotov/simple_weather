package com.dpanayotov.simpleweather.general;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Application;
import android.content.Context;

public class SimpleWeatherApplication extends Application {
	private static SimpleWeatherApplication mInstance;
	private static Gson mGson;

	public SimpleWeatherApplication() {
		mInstance = this;
		//mGson = new Gson();
		mGson = new GsonBuilder().setPrettyPrinting().create();
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
}
