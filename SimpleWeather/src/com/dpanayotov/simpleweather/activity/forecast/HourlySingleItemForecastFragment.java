package com.dpanayotov.simpleweather.activity.forecast;

import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.Constants;

public class HourlySingleItemForecastFragment extends
		SingleItemForecastFragment {

	@Override
	public Forecast getForecast() {
		int forecastID = getArguments().getInt(Constants.PARAM_FORECAST_ID);
		IForecastDataProvider dataProvider = (IForecastDataProvider) getActivity();
		return dataProvider.getForecastData().getHourly().getData()
				.get(forecastID);
	}

}
