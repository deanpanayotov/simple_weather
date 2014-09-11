package com.dpanayotov.simpleweather.activity.forecast;

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

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fillInData();
	}

	public abstract Forecast getForecast();

	private void fillInData() {
		ForecastResponse forecastResponse = ((IForecastDataProvider) getActivity())
				.getForecastData();
		((ImageView) getView().findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil
						.returnImageResource(getForecast().getIcon()));
		((TextView) getView().findViewById(R.id.temperature))
				.setText(getString(R.string.temperature) + ": "
						+ getForecastTemperature());
		((TextView) getView().findViewById(R.id.precip_intensity))
				.setText(getString(R.string.precip_intensity) + ": "
						+ getForecast().getPrecipIntensity());
		((TextView) getView().findViewById(R.id.precip_probability))
				.setText(getString(R.string.precip_probability) + ": "
						+ getForecast().getPrecipProbability());
		((TextView) getView().findViewById(R.id.humitidy))
				.setText(getString(R.string.humitidy) + ": "
						+ getForecast().getHumidity());
		((TextView) getView().findViewById(R.id.wind_speed))
				.setText(getString(R.string.wind_speed) + ": "
						+ getForecast().getWindSpeed());
		((TextView) getView().findViewById(R.id.cloud_cover))
				.setText(getString(R.string.cloud_cover) + ": "
						+ getForecast().getCloudCover());
	}

	private String getForecastTemperature() {
		if (getForecast().getTemperatureMin() == 0f
				&& getForecast().getTemperatureMax() == 0f) {
			return "" + getForecast().getTemperature();
		} else {
			return getForecast().getTemperatureMin() + " / "
					+ getForecast().getTemperatureMax();
		}
	}
}
