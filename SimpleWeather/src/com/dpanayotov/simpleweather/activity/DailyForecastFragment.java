package com.dpanayotov.simpleweather.activity;

import java.util.List;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;

public class DailyForecastFragment extends ForecastFragment {

	@Override
	public int getLayoutResourceId() {
		return R.layout.fragment_daily_hourly_forecast;
	}

	@Override
	public String getSummaryIcon() {
		return getResponse().getDaily().getIcon();
	}

	@Override
	public String getSummaryText() {
		return getResponse().getDaily().getSummary();
	}

	@Override
	public String getTitleBarText() {
		return getActivity().getString(R.string.title_daily);
	}

	@Override
	public List<Forecast> getForecastList() {
		return getResponse().getDaily().getData();
	}

	@Override
	public boolean isDaily() {
		return true;
	}

}
