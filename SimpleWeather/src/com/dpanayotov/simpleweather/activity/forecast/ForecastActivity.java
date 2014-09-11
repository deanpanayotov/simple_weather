package com.dpanayotov.simpleweather.activity.forecast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.Response;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.BaseSWActivity;
import com.dpanayotov.simpleweather.activity.forecast.fragment.item.CurrentWeatherFragment;
import com.dpanayotov.simpleweather.activity.forecast.fragment.list.DailyForecastFragment;
import com.dpanayotov.simpleweather.activity.forecast.fragment.list.HourlyForecastFragment;
import com.dpanayotov.simpleweather.api.request.CurrentForecastParams;
import com.dpanayotov.simpleweather.api.request.CurrentForecastRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.general.RequestManager;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.LogUtil;
import com.google.android.gms.maps.model.LatLng;

public class ForecastActivity extends BaseSWActivity implements
		IForecastDataProvider {
	private String FORECAST_REQUEST_TAG = "FORECAST_REQUEST_TAG";

	private ForecastResponse mForecastResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		LatLng latlng = ((LatLng) getIntent().getParcelableExtra(
				Constants.PARAM_LATLNG));
		RequestManager.sendServerRequest(this, FORECAST_REQUEST_TAG,
				new CurrentForecastRequest(new CurrentForecastParams(
						(float) latlng.latitude, (float) latlng.longitude),
						this), new Response.Listener<ForecastResponse>() {

					@Override
					public void onResponse(ForecastResponse response) {
						response.selfValidate();
						mForecastResponse = response;
						((ViewPager) findViewById(R.id.pager))
								.setAdapter(new ForecastPagerAdapter(
										getSupportFragmentManager()));
					}
				});
	}

	private class ForecastPagerAdapter extends FragmentPagerAdapter {

		public ForecastPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return ForecastActivity.this
						.getString(R.string.pager_title_currently);
			case 1:
				return ForecastActivity.this
						.getString(R.string.pager_title_hourly);
			case 2:
				return ForecastActivity.this
						.getString(R.string.pager_title_daily);
			default:
				LogUtil.e("default statement in getPageTitle() fragment pager adapter reached!");
				return ForecastActivity.this
						.getString(R.string.pager_title_currently);
			}

		}

		@Override
		public Fragment getItem(int pos) {
			switch (pos) {

			case 0:
				return new CurrentWeatherFragment();
			case 1:
				return new HourlyForecastFragment();
			case 2:
				return new DailyForecastFragment();
			default:
				LogUtil.e("default statement in getItem() fragment pager adapter reached!");
				return new CurrentWeatherFragment();
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

	@Override
	public ForecastResponse getForecastData() {
		return mForecastResponse;
	}
}