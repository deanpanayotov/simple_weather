package com.dpanayotov.simpleweather.activity.forecast.fragment.list;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.BitmapCirclesUtil;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

public class ForecastAdapter extends ArrayAdapter<Forecast> {
	private Context mContext;
	private boolean mIsDaily;

	public ForecastAdapter(Context context, List<Forecast> objects,
			boolean isDaily) {
		super(context, R.layout.layout_forecast_item, objects);
		mContext = context;
		mIsDaily = isDaily;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Forecast f = getItem(position);
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.layout_forecast_item, null);
		}
		String formattedTime = DateUtil.getFormatedDate(f.getTime(),
				mIsDaily ? DateUtil.FORECAST_LIST_FORMAT_DAILY
						: DateUtil.FORECAST_LIST_FORMAT_HOURLY);
		v.findViewById(R.id.midnight_bar).setVisibility(
				!mIsDaily
						&& DateUtil.FORECAST_LIST_MIDNIGHT
								.equals(formattedTime) ? View.VISIBLE
						: View.GONE);

		v.findViewById(R.id.item).setBackgroundColor(
				mContext.getResources().getColor(
						position % 2 == 0 ? R.color.terra_terra
								: R.color.terra_terra_terra));
		((TextView) v.findViewById(R.id.text)).setText(formattedTime);
		((ImageView) v.findViewById(R.id.icon))
				.setImageResource(WeatherImageUtil.returnImageResource(f
						.getIcon()));
		((ImageView) v.findViewById(R.id.precip))
				.setImageBitmap(BitmapCirclesUtil.getPrecipCircle(
						f.getPrecipIntensity(), f.getPrecipProbability()));
		float temperature;
		if (mIsDaily) {
			temperature = (f.getTemperatureMin() + f.getTemperatureMax()) / 2;
		} else {
			temperature = f.getTemperature();
		}
		((ImageView) v.findViewById(R.id.temperature))
				.setImageBitmap(BitmapCirclesUtil
						.getTemperatureCircle(temperature));
		((ImageView) v.findViewById(R.id.coluds))
				.setImageBitmap(BitmapCirclesUtil.getCloudCircle(f
						.getCloudCover()));
		((ImageView) v.findViewById(R.id.wind))
				.setImageBitmap(BitmapCirclesUtil.getWindCircle(f
						.getWindSpeed()));

		return v;
	}
}