package com.dpanayotov.simpleweather.api.response;

import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;
import com.google.gson.annotations.SerializedName;

public class ForecastResponse extends BaseForecastResponse {
	@SerializedName("currently")
	private Forecast currently;
	@SerializedName("hourly")
	private ForecastArray hourly;
	@SerializedName("daily")
	private ForecastArray daily;

	public Forecast getCurrently() {
		return currently;
	}

	public ForecastArray getHourly() {
		return hourly;
	}

	public ForecastArray getDaily() {
		return daily;
	}

	@Override
	public String toString() {
		return "ForecastResponse [currently=" + currently + ", hourly="
				+ hourly + ", daily=" + daily + "]";
	}

}
