package com.dpanayotov.simpleweather.api.base;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

public abstract class BaseForecastParams {

	public static final String COMMA = ",";
	public static final String SLASH = "/";

	/**
	 * 
	 * @return full api url with key and comma separated request params
	 */
	public final String build() {
		StringBuilder sb = new StringBuilder();
		sb.append(SimpleWeatherApplication.getContext().getString(
				R.string.forecastio_api_url));
		sb.append(SLASH);
		sb.append(SimpleWeatherApplication.getContext().getString(
				R.string.forecastio_api_key));
		sb.append(SLASH);
		sb.append(buildParams());
		return sb.toString();
	}

	/**
	 * 
	 * @return comma separated request params
	 */
	protected abstract String buildParams();

}
