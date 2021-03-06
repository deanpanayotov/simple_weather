package com.dpanayotov.simpleweather.activity.forecast.fragment.item;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.forecast.IForecastDataProvider;
import com.dpanayotov.simpleweather.api.response.Forecast;

public class CurrentWeatherFragment extends SingleItemForecastFragment {

	@Override
	public Forecast getForecast() {
		return ((IForecastDataProvider) getActivity()).getForecastData()
				.getCurrently();
	}

	@Override
	public String getTitle() {
		return getString(R.string.currently);
	}
}
