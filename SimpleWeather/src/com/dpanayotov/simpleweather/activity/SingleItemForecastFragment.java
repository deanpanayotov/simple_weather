package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

public abstract class SingleItemForecastFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.single_item_forecast, container,
				false);

		return root;

	}

	public abstract Forecast getForecast();

	private void fillInData(View root) {
		ForecastResponse forecastResponse = ((IForecastDataProvider) getActivity())
				.getForecastData();
		((ImageView) root.findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil
						.returnImageResource(getForecast().getIcon()));
		((TextView) root.findViewById(R.id.temperature))
				.setText(getString(R.id.temperature) + ": "
						+ getForecastTemperature());
		((TextView) root.findViewById(R.id.precip_intensity))
		.setText(getString(R.id.precip_intensity) + ": "
				+ getForecast().getPrecipIntensity());
		((TextView) root.findViewById(R.id.precip_probability))
		.setText(getString(R.id.precip_probability) + ": "
				+ getForecast().getPrecipProbability());
		((TextView) root.findViewById(R.id.humitidy))
		.setText(getString(R.id.humitidy) + ": "
				+ getForecast().getHumidity());
		((TextView) root.findViewById(R.id.wind_speed))
		.setText(getString(R.id.wind_speed) + ": "
				+ getForecast().getWindSpeed());
		((TextView) root.findViewById(R.id.cloud_cover))
		.setText(getString(R.id.cloud_cover) + ": "
				+ getForecast().getCloudCover());
	}

	private String getForecastTemperature() {
		if (getForecast().getTemperature() != 0f) {
			return "" + getForecast().getTemperature();
		} else {
			return getForecast().getTemperatureMin() + " / "
					+ getForecast().getTemperatureMax();
		}
	}
}
