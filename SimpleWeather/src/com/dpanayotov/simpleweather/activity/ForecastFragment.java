package com.dpanayotov.simpleweather.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

public abstract class ForecastFragment extends Fragment {

	/**
	 * @return the resource id of the layout with which the root view is
	 *         inflated in
	 *         {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
	 */
	public abstract int getLayoutResourceId();

	public abstract String getSummaryIcon();

	public abstract String getSummaryText();

	public abstract String getTitleBarText();

	public abstract List<Forecast> getForecastList();

	public abstract boolean isDaily();

	private ForecastResponse mResponse;

	protected ForecastResponse getResponse() {
		return mResponse;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(getLayoutResourceId(), container, false);
		return root;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mResponse = ((IForecastDataProvider) getActivity()).getForecastData();
		initSummary();
		initForecastList();
	}

	private void initSummary() {
		((TextView) getView().findViewById(R.id.title_bar))
				.setText(getTitleBarText());
		((ImageView) getView().findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil
						.returnImageResource(getSummaryIcon()));
		((TextView) getView().findViewById(R.id.summary))
				.setText(getSummaryText());
	}

	private void initForecastList() {
		((ListView) getView().findViewById(R.id.list))
				.setAdapter(new ForecastAdapter(getActivity(),
						getForecastList(), isDaily()));

	}
}
