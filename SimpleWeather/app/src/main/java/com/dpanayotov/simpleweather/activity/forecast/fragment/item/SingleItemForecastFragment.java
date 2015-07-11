package com.dpanayotov.simpleweather.activity.forecast.fragment.item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;
import com.dpanayotov.simpleweather.util.UnitUtil;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class SingleItemForecastFragment extends Fragment {

	private static final String PERCENT = SimpleWeatherApplication.getContext()
			.getString(R.string.percent);
	private static final String BULLET = SimpleWeatherApplication.getContext()
			.getString(R.string.bullet);

    @Bind(R.id.data) View data;
    @Bind(R.id.no_data) View noData;

    @Bind(R.id.summary) TextView summary;
    @Bind(R.id.icon) ImageView icon;
    @Bind(R.id.temperature) TextView temperature;
    @Bind(R.id.precip_intensity) TextView precipIntensity;
    @Bind(R.id.precip_probability) TextView precipProbability;
    @Bind(R.id.wind_speed) TextView windSpeed;
    @Bind(R.id.cloud_cover) TextView cloudCover;
    @Bind(R.id.humitidy) TextView humitidy;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_single_item_forecast,
				container, false);
        ButterKnife.bind(this, root);
        Log.d("test41", "onCreateView");
		return root;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
        Log.d("test41", "onActivityCreated");
		fillInData();
	}

	public abstract Forecast getForecast();

	public abstract String getTitle();

	private void fillInData() {
		if (getForecast().getIcon() == null
				&& getForecast().getSummary() == null) {
			data.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
		} else {
			data.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);

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

			summary.setText(getTitle() + getForecast().getSummary());
			icon.setImageResource(WeatherImageUtil
					.returnImageResource(getForecast().getIcon()));
			temperature.setText(sTemperature);
			precipIntensity.setText(sPercipIntensity);
			precipProbability.setText(sPercipProbability);
			windSpeed.setText(sWindSPeed);
			cloudCover.setText(sCloudCover);
			humitidy.setText(sHumitidy);
		}
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
