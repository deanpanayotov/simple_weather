package com.dpanayotov.simpleweather.activity.forecast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.volley.Response;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.MapActivity;
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
import com.dpanayotov.simpleweather.util.LocationUtil;
import com.dpanayotov.simpleweather.util.LogUtil;
import com.google.android.gms.maps.model.LatLng;

public class ForecastActivity extends BaseSWActivity implements
		IForecastDataProvider, LocationListener {
	private String FORECAST_REQUEST_TAG = "FORECAST_REQUEST_TAG";

	private LocationManager mLocationManager;
	private boolean mSettingsOpened = false;
	private boolean mLocationFetched = false;
	private long mLocationFetchTime = 15 * DateUtil.SECOND;
	private ForecastResponse mForecastResponse;

	private Handler mHandler = new Handler();
	private Runnable mRunnableStartMap = new Runnable() {

		@Override
		public void run() {
			if (!mLocationFetched) {
				mLocationFetched = true; // to cancel the runnable below
				hideProgressDialog();
				startMapActivityNoLocation();
			}
		}
	};
	private Runnable mRunnableGetLocation = new Runnable() {

		@Override
		public void run() {
			if (!mLocationFetched) {
				Location location = getLastKnownLocation();
				if (location != null) {
					mLocationFetched = true;
					hideProgressDialog();
					getWeatherForLocation(new LatLng(location.getLatitude(),
							location.getLongitude()));
				} else {
					mHandler.postDelayed(mRunnableGetLocation, DateUtil.SECOND);
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forecast);
		if (savedInstanceState != null) {
			mSettingsOpened = savedInstanceState
					.getBoolean(Constants.PARAM_SETTINGS_OPEN);
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
			if (latlng == null) {
				mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

				if (LocationUtil.areLocationServicesEnabled(this)) {
					getLastKnowLocationAndGetWeather(true);
				} else {
					LocationUtil.openLocationEnableDialog(this,
							new OnClickListener() {

								@Override
								public void onClick(
										DialogInterface paramDialogInterface,
										int paramInt) {
									Intent myIntent = new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									mSettingsOpened = true;
									startActivity(myIntent);
								}
							}, new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									mSettingsOpened = false;
									startMapActivityNoLocation();
								}
							});
				}
			} else {
				getWeatherForLocation(latlng);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mSettingsOpened) {
			mSettingsOpened = false;
			if (LocationUtil.areLocationServicesEnabled(this)) {
				getLastKnowLocationAndGetWeather(true);
			} else {
				startMapActivityNoLocation();
			}
		}
	}

	private void getLastKnowLocationAndGetWeather(boolean requestLocationUpdates) {
		Location location = getLastKnownLocation();
		if (location != null) {
			getWeatherForLocation(new LatLng(location.getLatitude(),
					location.getLongitude()));
		} else {
			if (requestLocationUpdates) {
				mLocationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 0, 0, this);
				mHandler.postDelayed(mRunnableStartMap, mLocationFetchTime);
				mHandler.postDelayed(mRunnableGetLocation, DateUtil.SECOND);
				showProgressDialog();
			}
		}
	}

	private Location getLastKnownLocation() {
		Location location = mLocationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			location = mLocationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		return location;
	}

	private void startMapActivityNoLocation() {
		Intent intent = new Intent(this, MapActivity.class);
		intent.putExtra(Constants.PARAM_NO_LOCATION, true);
		startActivity(intent);
	}

	private void getWeatherForLocation(LatLng latlng) {
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
		outState.putBoolean(Constants.PARAM_SETTINGS_OPEN, mSettingsOpened);
		outState.putParcelable(Constants.PARAM_FULL_FORECAST_RESPONSE,
				mForecastResponse);
		outState.putString(Constants.PARAM_LOCATION_NAME, getActionBar()
				.getTitle().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
			mLocationFetched = true;
			mLocationManager.removeUpdates(this);
			hideProgressDialog();
			getWeatherForLocation(new LatLng(location.getLatitude(),
					location.getLongitude()));
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