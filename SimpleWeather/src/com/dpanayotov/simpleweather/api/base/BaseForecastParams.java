package com.dpanayotov.simpleweather.api.base;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

public abstract class BaseForecastParams {

	public static final String COMMA = ",";
	public static final String SLASH = "/";

	public static final String CURRENTLY = "currently";
	public static final String MINUTELY = "minutely";
	public static final String HOURLY = "hourly";
	public static final String DAILY = "daily";
	public static final String ALERTS = "alerts";
	public static final String FLAGS = "flags";

	/**
	 * @return response blocks that should be omitted
	 */
	public abstract String[] getExcludedBlocks();

	/**
	 * 
	 * @return full api url with key and comma separated request params, and
	 *         option params at the end
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
		sb.append(buildExcludeOption());
		sb.append(buildUnitsOption());
		return sb.toString();
	}

	/**
	 * Implemented due to performance reasons.
	 * 
	 * @return a ready-to-append exclude parameter option, selecting all blocks
	 *         that shouldn't be returned in the response;
	 */
	private final String buildExcludeOption() {
		String[] exclude = getExcludedBlocks();
		if (exclude == null || exclude.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("?exclude=");
		for (int i = 0; i < exclude.length - 1; i++) {
			sb.append(exclude[i]);
			sb.append(COMMA);
		}
		sb.append(exclude[exclude.length - 1]);
		return sb.toString();
	}

	/**
	 * @return a ready-to-append units parameter option defining which unit
	 *         system should be used for the exact measurements returned by the
	 *         response
	 */
	private final String buildUnitsOption() {
		return "?units=" + SimpleWeatherApplication.getUnits();
	}

	/**
	 * 
	 * @return comma separated request params
	 */
	protected abstract String buildParams();

}
