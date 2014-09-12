package com.dpanayotov.simpleweather.activity.forecast;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.activity.forecast.fragment.item.HourlySingleItemForecastFragment;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.DateUtil;

public abstract class SingleForecastViewActivity extends BaseSWActivity
		implements IForecastDataProvider {
	private ForecastResponse mForecastResponse;
	private List<Forecast> forecasts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		mForecastResponse = getIntent().getParcelableExtra(
				Constants.PARAM_FULL_FORECAST_RESPONSE);
		String title = getIntent()
				.getStringExtra(Constants.PARAM_LOCATION_NAME);
		if (title != null && title.length() > 0) {
			getActionBar().setTitle(title);
		}
		forecasts = getForecasts();
		SingleItemForecastPagerAdapter adapter = new SingleItemForecastPagerAdapter(
				getSupportFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		pager.setCurrentItem(getIntent().getIntExtra(
				Constants.PARAM_FORECAST_ID, 0));
	}

	private class SingleItemForecastPagerAdapter extends
			FragmentStatePagerAdapter {

		public SingleItemForecastPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = getItemFragment();
			Bundle arguments = new Bundle();
			arguments.putInt(Constants.PARAM_FORECAST_ID, position);
			fragment.setArguments(arguments);
			return fragment;
		}

		@Override
		public int getCount() {
			return forecasts.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return DateUtil.getFormatedDate(forecasts.get(position).getTime(),
					getDateFormat());
		}
	}

	@Override
	public ForecastResponse getForecastData() {
		return mForecastResponse;
	}

	public abstract List<Forecast> getForecasts();

	public abstract Fragment getItemFragment();

	public abstract String getDateFormat();

}
