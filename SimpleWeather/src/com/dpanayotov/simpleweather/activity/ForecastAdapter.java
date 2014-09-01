package com.dpanayotov.simpleweather.activity;

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
import com.dpanayotov.simpleweather.util.LogUtil;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

class ForecastAdapter extends ArrayAdapter<Forecast> {
	Context mContext;

	public ForecastAdapter(Context context, List<Forecast> objects) {
		super(context, R.layout.layout_forecast_item, objects);
		mContext = context;
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
				DateUtil.FORECAST_LIST_FORMAT);
		v.findViewById(R.id.midnight_bar).setVisibility(
				"00:00".equals(formattedTime) ? View.VISIBLE : View.GONE);

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
		LogUtil.d("Precipintensity: " + f.getPrecipIntensity()
				+ " Precipprobability: " + f.getPrecipProbability());
		((ImageView) v.findViewById(R.id.temperature))
				.setImageBitmap(BitmapCirclesUtil.getTemperatureCircle(f
						.getTemperature()));
		((ImageView) v.findViewById(R.id.coluds))
				.setImageBitmap(BitmapCirclesUtil.getCloudCircle(f
						.getCloudCover()));
		((ImageView) v.findViewById(R.id.wind))
				.setImageBitmap(BitmapCirclesUtil.getWindCircle(f
						.getWindSpeed()));

		return v;
	}
}