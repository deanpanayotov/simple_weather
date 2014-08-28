package com.dpanayotov.simpleweather.api.response;

import com.google.gson.annotations.SerializedName;

public class Forecast {
	@SerializedName("summary")
	private String summary;
	@SerializedName("icon")
	private String icon;
	@SerializedName("precipIntensity")
	private float precipIntensity;
	@SerializedName("precipProbability")
	private float precipProbability;
	@SerializedName("precipType")
	private String precipType;
	@SerializedName("temperatureMin")
	private float temperatureMin;
	@SerializedName("temperatureMax")
	private float temperatureMax;
	@SerializedName("temperature")
	private float temperature;
	@SerializedName("humidity")
	private float humidity;
	@SerializedName("windSpeed")
	private float windSpeed;
	@SerializedName("windBearing")
	private int windBearing;
	@SerializedName("cloudCover")
	private float cloudCover;

	public String getSummary() {
		return summary;
	}

	public String getIcon() {
		return icon;
	}

	public float getPrecipIntensity() {
		return precipIntensity;
	}

	public float getPrecipProbability() {
		return precipProbability;
	}

	public String getPrecipType() {
		return precipType;
	}

	public float getTemperatureMin() {
		return temperatureMin;
	}

	public float getTemperatureMax() {
		return temperatureMax;
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public int getWindBearing() {
		return windBearing;
	}

	public float getCloudCover() {
		return cloudCover;
	}

	@Override
	public String toString() {
		return "Forecast [summary=" + summary + ", icon=" + icon
				+ ", precipIntensity=" + precipIntensity
				+ ", precipProbability=" + precipProbability + ", precipType="
				+ precipType + ", temperatureMin=" + temperatureMin
				+ ", temperatureMax=" + temperatureMax + ", temperature="
				+ temperature + ", humidity=" + humidity + ", windSpeed="
				+ windSpeed + ", windBearing=" + windBearing + ", cloudCover="
				+ cloudCover + "]";
	}

}
