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
			HourlySingleItemForecastFragment fragment = new HourlySingleItemForecastFragment();
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
					DateUtil.FORECAST_SINGLE_ITEM_FORMAT_HOURLY);
		}
	}

	@Override
	public ForecastResponse getForecastData() {
		return mForecastResponse;
	}

}
