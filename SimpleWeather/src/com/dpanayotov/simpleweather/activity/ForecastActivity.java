package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.LogUtil;

public class ForecastActivity extends BaseSWActivity implements
		IForecastDataProvider {

	private ForecastResponse mForecastResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new ForecastPagerAdapter(getSupportFragmentManager()));
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
				return new CurrentForecastFragment();
			case 1:
				return new HourlyForecastFragment();
			case 2:
				return new DailyForecastFragment();
			default:
				LogUtil.e("default statement in getItem() fragment pager adapter reached!");
				return new CurrentForecastFragment();
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