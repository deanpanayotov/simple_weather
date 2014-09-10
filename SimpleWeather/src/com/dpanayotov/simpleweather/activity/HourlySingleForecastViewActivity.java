package com.dpanayotov.simpleweather.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.DateUtil;

public class HourlySingleForecastViewActivity extends BaseSWActivity implements
		IForecastDataProvider {
	private ForecastResponse mForecastResponse;
	private List<Forecast> forecasts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		mForecastResponse = getIntent().getParcelableExtra(
				Constants.PARAM_FULL_FORECAST_RESPONSE);
		forecasts = mForecastResponse.getHourly().getData();
		((ViewPager) findViewById(R.id.pager))
				.setAdapter(new SingleItemForecastPagerAdapter(
						getSupportFragmentManager()));
	}

	private class SingleItemForecastPagerAdapter extends
			FragmentStatePagerAdapter {

		public SingleItemForecastPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return new HourlySingleItemForecastFragment();
		}

		@Override
		public int getCount() {
			return forecasts.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return DateUtil.getFormatedDate(forecasts.get(position).getTime(),
					DateUtil.FORECAST_SINGLE_ITEM_FORMAT_HOURLY);
		}
	}

	@Override
	public ForecastResponse getForecastData() {
		return mForecastResponse;
	}

}
