package com.dpanayotov.simpleweather.activity.forecast;

import java.util.List;

import android.support.v4.app.Fragment;

import com.dpanayotov.simpleweather.activity.forecast.fragment.item.HourlySingleItemForecastFragment;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.DateUtil;

public class HourlySingleForecastViewActivity extends
		SingleForecastViewActivity {

	@Override
	public List<Forecast> getForecasts() {
		return getForecastData().getHourly().getData();
	}

	@Override
	public Fragment getItemFragment() {
		return new HourlySingleItemForecastFragment();
	}

	@Override
	public String getDateFormat() {
		return DateUtil.FORECAST_SINGLE_ITEM_FORMAT_HOURLY;
	}

}
