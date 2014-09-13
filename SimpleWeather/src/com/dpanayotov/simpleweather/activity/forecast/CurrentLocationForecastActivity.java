package com.dpanayotov.simpleweather.activity.forecast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.dpanayotov.simpleweather.activity.MapActivity;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.LocationUtil;
import com.google.android.gms.maps.model.LatLng;

public class CurrentLocationForecastActivity extends ForecastActivity {
	private LocationManager mLocationManager;
	private boolean mSettingsOpened = false;
	private boolean mLocationFetched = false;
	private boolean mRunLocationFetchEverySecond = true;
	private long mLocationFetchTime = 15 * DateUtil.SECOND;

	private Handler mHandler = new Handler();
	private Runnable mRunnableStartMap = new Runnable() {

		@Override
		public void run() {
			if (!mLocationFetched) {
				mRunLocationFetchEverySecond = false; // to cancel the runnable
														// below
				hideProgressDialog();
				startMapActivityNoLocation();
			}
		}
	};
	private Runnable mRunnableGetLocation = new Runnable() {

		@Override
		public void run() {
			if (mRunLocationFetchEverySecond) {
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
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (savedInstanceState != null) {
			mSettingsOpened = savedInstanceState
					.getBoolean(Constants.PARAM_SETTINGS_OPEN);
		} else {
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
		}
	};

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
				// mLocationManager.requestLocationUpdates(
				// LocationManager.GPS_PROVIDER, 0, 0, this);
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(Constants.PARAM_SETTINGS_OPEN, mSettingsOpened);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected boolean doubleTapBack() {
		return true;
	}
}
