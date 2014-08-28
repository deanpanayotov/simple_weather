package com.dpanayotov.simpleweather.api.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ForecastArray {
	@SerializedName("summary")
	private String summary;
	@SerializedName("icon")
	private String icon;
	@SerializedName("data")
	private List<Forecast> data;

	public String getSummary() {
		return summary;
	}

	public String getIcon() {
		return icon;
	}

	public List<Forecast> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "ForecastArray [summary=" + summary + ", icon=" + icon
				+ ", data=" + data + "]";
	}

}
