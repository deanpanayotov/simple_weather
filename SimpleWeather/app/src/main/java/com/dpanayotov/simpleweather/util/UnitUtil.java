package com.dpanayotov.simpleweather.util;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

/**
 * Some values are duplicated intentionally - see forecast.io documentation:
 * https://developer.forecast.io/docs/v2
 */
public class UnitUtil {
	public static String getTemperatureUnit() {
		return getUnitString(R.string.temperature_unit_si,
				R.string.temperature_unit_us, R.string.temperature_unit_si,
				R.string.temperature_unit_si);
	}

	public static String getPrecipIntensityUnit() {
		return getUnitString(R.string.precip_intensity_unit_si,
				R.string.precip_intensity_unit_us,
				R.string.temperature_unit_si, R.string.temperature_unit_si);

	}

	public static String getWindSpeedUnit() {
		return getUnitString(R.string.wind_speed_unit_si,
				R.string.wind_speed_unit_us, R.string.wind_speed_unit_ca,
				R.string.wind_speed_unit_us);

	}

	private static String getUnitString(int resIdSi, int resIdUs, int resIdCa,
			int resIdUk) {
		int id = 0;
		switch (SimpleWeatherApplication.getUnits()) {
		case SI:
			id = resIdSi;
			break;

		case US:
			id = resIdUs;
			break;

		case CA:
			id = resIdCa;
			break;

		case UK:
			id = resIdUk;
			break;

		default:
			id = resIdSi;
			break;
		}
		return SimpleWeatherApplication.getContext().getString(id);
	}

}
