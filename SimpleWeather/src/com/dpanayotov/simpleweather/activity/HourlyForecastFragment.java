package com.dpanayotov.simpleweather.activity;

import java.util.List;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;

public class HourlyForecastFragment extends ForecastFragment {

	@Override
	public int getLayoutResourceId() {
		return R.layout.fragment_daily_hourly_forecast;
	}

	@Override
	public String getSummaryIcon() {
		return getResponse().getHourly().getIcon();
	}

	@Override
	public String getSummaryText() {
		return getResponse().getHourly().getSummary();
	}

	@Override
	public String getTitleBarText() {
		return getActivity().getString(R.string.title_hourly);
	}

	@Override
	public List<Forecast> getForecastList() {
		return getResponse().getHourly().getData();
	}

	@Override
	public boolean isDaily() {
		return false;
	}

}
