package com.dpanayotov.simpleweather.util;

import com.dpanayotov.simpleweather.R;

public class WeatherImageUtil {
	public static final int returnImageResource(String icon) {
		if(icon==null){
			return 0;
		}
		switch (icon) {
		case "clear-day":
			return R.drawable.clear_day;
		case "clear-night":
			return R.drawable.clear_night;
		case "rain":
			return R.drawable.rain;
		case "snow":
			return R.drawable.snow;
		case "sleet":
			return R.drawable.sleet;
		case "wind":
			return R.drawable.wind;
		case "fog":
			return R.drawable.fog;
		case "cloudy":
			return R.drawable.cloudy;
		case "partly-cloudy-day":
			return R.drawable.partly_cloudy_day;
		case "partly-cloudy-night":
			return R.drawable.partly_cloudy_night;
		default:
			return R.drawable.default_placeholder;
		}
	}
}
