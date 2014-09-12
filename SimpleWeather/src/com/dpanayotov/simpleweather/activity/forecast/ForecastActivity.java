package com.dpanayotov.simpleweather.activity.forecast;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.Response;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.activity.forecast.fragment.item.CurrentWeatherFragment;
import com.dpanayotov.simpleweather.activity.forecast.fragment.list.DailyForecastFragment;
import com.dpanayotov.simpleweather.activity.forecast.fragment.list.HourlyForecastFragment;
import com.dpanayotov.simpleweather.api.request.CurrentForecastParams;
import com.dpanayotov.simpleweather.api.request.CurrentForecastRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.general.RequestManager;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.GeocodingUtil;
import com.dpanayotov.simpleweather.util.LogUtil;
import com.google.android.gms.maps.model.LatLng;

public class ForecastActivity extends BaseSWActivity implements
		IForecastDataProvider, LocationListener{
	private String FORECAST_REQUEST_TAG = "FORECAST_REQUEST_TAG";

    private LocationManager mLocationManager;
	private ForecastResponse mForecastResponse;
	private static final long LOCATION_EXPIRE_TIME = DateUtil.MINUTE * 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		if (savedInstanceState != null) {
			mForecastResponse = savedInstanceState
					.getParcelable(Constants.PARAM_FULL_FORECAST_RESPONSE);
			getActionBar()
					.setTitle(
							savedInstanceState
									.getString(Constants.PARAM_LOCATION_NAME));
			((ViewPager) findViewById(R.id.pager))
					.setAdapter(new ForecastPagerAdapter(
							getSupportFragmentManager()));
		} else {
			LatLng latlng = ((LatLng) getIntent().getParcelableExtra(
					Constants.PARAM_LATLNG));
			if(latlng == null){
				LogUtil.d("#######latlng == null");
				mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		        if(location != null) {				LogUtil.d("#######latlng == null");
					LogUtil.d("#######good");
		        	getWeatherForLocation(new LatLng(location.getLatitude(), location.getLongitude()));
		        }
		        else {
					LogUtil.d("#######bad");
		            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		            showProgressDialog();
		        }
			}else{
				getWeatherForLocation(latlng);
			}
		}
	}
		
	private void getWeatherForLocation(LatLng latlng){
		getActionBar().setTitle(GeocodingUtil.getGeocodeName(latlng));
		RequestManager.sendServerRequest(this, FORECAST_REQUEST_TAG,
				new CurrentForecastRequest(new CurrentForecastParams(
						(float) latlng.latitude, (float) latlng.longitude),
						this), new Response.Listener<ForecastResponse>() {

					@Override
					public void onResponse(ForecastResponse response) {
						response.simulateMissingBlocks(); // TODO
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(Constants.PARAM_FULL_FORECAST_RESPONSE,
				mForecastResponse);
		outState.putString(Constants.PARAM_LOCATION_NAME, getActionBar()
				.getTitle().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onLocationChanged(Location location) {
		LogUtil.d("#######received");
		if (location != null) {
            mLocationManager.removeUpdates(this);
            getWeatherForLocation(new LatLng(location.getLatitude(), location.getLongitude()));
            hideProgressDialog();
        }
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}