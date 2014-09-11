package com.dpanayotov.simpleweather.activity.forecast.fragment.item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.forecast.IForecastDataProvider;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.UnitUtil;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

public abstract class SingleItemForecastFragment extends Fragment {

	private static final String PERCENT = " %";

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
		((ImageView) getView().findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil
						.returnImageResource(getForecast().getIcon()));
		((TextView) getView().findViewById(R.id.temperature))
				.setText(getForecastTemperature()
						+ UnitUtil.getTemperatureUnit());
		((TextView) getView().findViewById(R.id.precip_intensity))
				.setText(getString(R.string.precip_intensity)
						+ getForecast().getPrecipIntensity()
						+ UnitUtil.getPrecipIntensityUnit());
		((TextView) getView().findViewById(R.id.precip_probability))
				.setText(getString(R.string.precip_probability) + 100
						* getForecast().getPrecipProbability() + PERCENT);
		((TextView) getView().findViewById(R.id.humitidy))
				.setText(getString(R.string.humitidy) + 100
						* getForecast().getHumidity() + PERCENT);
		((TextView) getView().findViewById(R.id.wind_speed))
				.setText(getString(R.string.wind_speed)
						+ getForecast().getWindSpeed()
						+ UnitUtil.getWindSpeedUnit());
		((TextView) getView().findViewById(R.id.cloud_cover))
				.setText(getString(R.string.cloud_cover) + 100
						* getForecast().getCloudCover() + PERCENT);
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
