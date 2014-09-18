package com.dpanayotov.simpleweather.api.request;

import com.dpanayotov.simpleweather.api.base.BaseForecastParams;

public class CurrentForecastParams extends BaseForecastParams {
	private float latitude;
	private float longitude;

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public CurrentForecastParams(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public CurrentForecastParams() {
	}

	@Override
	public String buildParams() {
		StringBuilder sb = new StringBuilder();
		sb.append(latitude);
		sb.append(COMMA);
		sb.append(longitude);
		return sb.toString();
	}

	@Override
	public String[] getExcludedBlocks() {
		return new String[] { MINUTELY, ALERTS, FLAGS };
	}

}
