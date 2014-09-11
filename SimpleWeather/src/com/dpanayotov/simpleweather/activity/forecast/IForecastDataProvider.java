package com.dpanayotov.simpleweather.activity.forecast;

import com.dpanayotov.simpleweather.api.response.ForecastResponse;

public interface IForecastDataProvider {
	public ForecastResponse getForecastData();
}
