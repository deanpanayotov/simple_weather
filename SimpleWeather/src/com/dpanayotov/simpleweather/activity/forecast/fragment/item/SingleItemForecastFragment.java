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
	private static final String BULLET = " • ";

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

		String sTemperature = BULLET + getForecastTemperature()
				+ UnitUtil.getTemperatureUnit();
		String sPercipIntensity = getString(R.string.precip_intensity)
				+ String.format("%.3f", getForecast().getPrecipIntensity())
				+ UnitUtil.getPrecipIntensityUnit();
		String sPercipProbability = getString(R.string.precip_probability)
				+ String.format("%.1f", 100 * getForecast()
						.getPrecipProbability()) + PERCENT;
		String sWindSPeed = getString(R.string.wind_speed)
				+ String.format("%.4f", getForecast().getWindSpeed())
				+ UnitUtil.getWindSpeedUnit();
		String sCloudCover = getString(R.string.cloud_cover)
				+ String.format("%.1f", 100 * getForecast().getCloudCover())
				+ PERCENT;
		String sHumitidy = getString(R.string.humitidy)
				+ String.format("%.1f", 100 * getForecast().getHumidity())
				+ PERCENT;

		TextView summary = (TextView) getView().findViewById(R.id.summary);
		ImageView image = (ImageView) getView().findViewById(R.id.icon);
		TextView temperature = (TextView) getView().findViewById(
				R.id.temperature);
		TextView precipIntensity = (TextView) getView().findViewById(
				R.id.precip_intensity);
		TextView precipProbability = (TextView) getView().findViewById(
				R.id.precip_probability);
		TextView windSpeed = (TextView) getView().findViewById(R.id.wind_speed);
		TextView cloudCover = (TextView) getView().findViewById(
				R.id.cloud_cover);
		TextView humitidy = (TextView) getView().findViewById(R.id.humitidy);

		summary.setText(getForecast().getSummary());
		image.setImageResource(WeatherImageUtil
				.returnImageResource(getForecast().getIcon()));
		temperature.setText(sTemperature);
		precipIntensity.setText(sPercipIntensity);
		precipProbability.setText(sPercipProbability);
		windSpeed.setText(sWindSPeed);
		cloudCover.setText(sCloudCover);
		humitidy.setText(sHumitidy);

	}

	private String getForecastTemperature() {
		if (getForecast().getTemperatureMin() == 0f
				&& getForecast().getTemperatureMax() == 0f) {
			return "" + String.format("%.2f", getForecast().getTemperature());
		} else {
			return String.format("%.2f", getForecast().getTemperatureMin())
					+ " / "
					+ String.format("%.2f", getForecast().getTemperatureMax());
		}
	}
}
