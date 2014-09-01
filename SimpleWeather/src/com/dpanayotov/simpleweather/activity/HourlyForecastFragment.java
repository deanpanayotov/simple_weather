package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

public class HourlyForecastFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_hourly_forecast,
				container, false);
		return root;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ForecastResponse response = ((IForecastDataProvider) getActivity())
				.getForecastData();
		initSummary(response);
		initForecastList(response);
	}

	private void initSummary(ForecastResponse response) {
		((ImageView) getView().findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil.returnImageResource(response
						.getCurrently().getIcon()));
		((TextView) getView().findViewById(R.id.summary)).setText(response
				.getCurrently().getSummary());
	}

	private void initForecastList(ForecastResponse response) {
		((ListView) getView().findViewById(R.id.list))
				.setAdapter(new ForecastAdapter(getActivity(), response
						.getHourly().getData(), false));

	}

}
