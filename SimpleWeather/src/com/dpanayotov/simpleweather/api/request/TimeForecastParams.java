package com.dpanayotov.simpleweather.api.request;

public class TimeForecastParams extends CurrentForecastParams {

	private long time;

	public void setTime(long time) {
		this.time = time;
	}

	public TimeForecastParams(float latitude, float longitude, long time) {
		super(latitude, longitude);
		this.time = time;
	}

	public TimeForecastParams() {

	}

	@Override
	public String buildParams() {
		StringBuilder sb = new StringBuilder(super.build());
		sb.append(COMMA);
		sb.append(time);
		return sb.toString();
	}
}
