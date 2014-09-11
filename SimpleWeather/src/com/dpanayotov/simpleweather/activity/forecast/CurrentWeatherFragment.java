package com.dpanayotov.simpleweather.activity.forecast;

import com.dpanayotov.simpleweather.api.response.Forecast;

public class CurrentWeatherFragment extends SingleItemForecastFragment {

	@Override
	public Forecast getForecast() {
		return ((IForecastDataProvider) getActivity()).getForecastData()
				.getCurrently();
	}

}
