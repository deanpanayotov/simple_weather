package com.dpanayotov.simpleweather.activity.forecast.fragment.list;

import java.util.List;

import android.view.View;
import android.widget.AdapterView;

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

	@Override
	public void onForecastItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		
	}

}