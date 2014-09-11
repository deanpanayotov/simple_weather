package com.dpanayotov.simpleweather.activity.forecast.fragment.list;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.forecast.HourlySingleForecastViewActivity;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.Constants;

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

	@Override
	public void onForecastItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		Intent intent = new Intent(getActivity(),
				HourlySingleForecastViewActivity.class);
		intent.putExtra(Constants.PARAM_FULL_FORECAST_RESPONSE, getResponse());
		intent.putExtra(Constants.PARAM_FORECAST_ID, (int) id); // THIS INT CAST
																// IS
																// MANDATORY!!!
		startActivity(intent);
	}

}
