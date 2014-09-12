package com.dpanayotov.simpleweather.activity.forecast;

import java.util.List;

import android.support.v4.app.Fragment;

import com.dpanayotov.simpleweather.activity.forecast.fragment.item.DailySingleItemForecastFragment;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.DateUtil;

public class DailySingleForecastViewActivity extends SingleForecastViewActivity {
	@Override
	public List<Forecast> getForecasts() {
		return getForecastData().getDaily().getData();
	}

	@Override
	public Fragment getItemFragment() {
		return new DailySingleItemForecastFragment();
	}

	@Override
	public String getDateFormat() {
		return DateUtil.FORECAST_SINGLE_ITEM_FORMAT_DAILY;
	}
}
