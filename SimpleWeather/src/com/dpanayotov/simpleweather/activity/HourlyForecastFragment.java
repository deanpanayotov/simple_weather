package com.dpanayotov.simpleweather.activity;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;

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
				.setImageResource(returnImageResource(response.getCurrently()
						.getIcon()));
		((TextView) getView().findViewById(R.id.summary)).setText(response
				.getCurrently().getSummary());
	}

	private void initForecastList(ForecastResponse response) {
		((ListView) getView().findViewById(R.id.list))
				.setAdapter(new ForecastAdapter(response.getHourly().getData()));

	}

	class ForecastAdapter extends ArrayAdapter<Forecast> {

		public ForecastAdapter(List<Forecast> objects) {
			super(getActivity(), R.layout.layout_forecast_item, objects);
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
			String formattedTime = DateFormat.format("HH:mm",
					f.getTime() * 1000).toString();
			v.findViewById(R.id.midnight_bar).setVisibility(
					"00:00".equals(formattedTime) ? View.VISIBLE : View.GONE);

			v.findViewById(R.id.item).setBackgroundColor(
					getActivity().getResources().getColor(
							position % 2 == 0 ? R.color.terra_terra
									: R.color.terra_terra_terra));
			((TextView) v.findViewById(R.id.text)).setText(formattedTime);
			((ImageView) v.findViewById(R.id.icon))
					.setImageResource(returnImageResource(f.getIcon()));
			((ImageView) v.findViewById(R.id.precip))
					.setImageBitmap(getDataCricle(f.getPrecipProbability()));
			((ImageView) v.findViewById(R.id.temperature))
					.setImageBitmap(getDataCricle(f.getTemperature() / 113.0f));
			((ImageView) v.findViewById(R.id.coluds))
					.setImageBitmap(getDataCricle(f.getCloudCover()));
			((ImageView) v.findViewById(R.id.wind))
					.setImageBitmap(getDataCricle(f.getWindSpeed() / 50.0f));

			return v;
		}
	}

	private int returnImageResource(String icon) {
		switch (icon) {
		case "clear-day":
			return R.drawable.clear_day;
		case "clear-night":
			return R.drawable.clear_night;
		case "rain":
			return R.drawable.rain;
		case "snow":
			return R.drawable.snow;
		case "sleet":
			return R.drawable.sleet;
		case "wind":
			return R.drawable.wind;
		case "fog":
			return R.drawable.fog;
		case "cloudy":
			return R.drawable.cloudy;
		case "partly-cloudy-day":
			return R.drawable.partly_cloudy_day;
		case "partly-cloudy-night":
			return R.drawable.partly_cloudy_night;
		default:
			return R.drawable.default_placeholder;
		}
	}

	private static final int BITMAP_SIZE = 200;

	private Bitmap getDataCricle(float size) {
		Bitmap bmp = Bitmap.createBitmap(BITMAP_SIZE, BITMAP_SIZE,
				Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bmp);
		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#000000"));
		c.drawCircle(BITMAP_SIZE / 2, BITMAP_SIZE / 2,
				(BITMAP_SIZE / 2) * size, paint);
		return bmp;
	}
}
