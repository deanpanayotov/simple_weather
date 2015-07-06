package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

public class SettingsActivity extends BaseSWActivity {
	Switch mValidationSwitch;
	Switch mCacheSwitch;
	Switch mMissingDataSwitch;
	Switch mUnitsSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initializeControls();
		setControlsState();
		setOnClickListeners();

	}

	private void initializeControls() {
		mValidationSwitch = (Switch) findViewById(R.id.switch_validation);
		mCacheSwitch = (Switch) findViewById(R.id.switch_cache);
		mMissingDataSwitch = (Switch) findViewById(R.id.switch_missing_data);
		mUnitsSwitch = (Switch) findViewById(R.id.switch_units);
	}

	private void setControlsState() {
		mValidationSwitch.setChecked(SimpleWeatherApplication.isDataValidationEnabled());
		mCacheSwitch.setChecked(SimpleWeatherApplication.isDBCahceEnabled());
		mMissingDataSwitch.setChecked(SimpleWeatherApplication.isMissingDataEnabled());
		mUnitsSwitch
				.setChecked(SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US);
	}

	private void setOnClickListeners() {
		mValidationSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						SimpleWeatherApplication.setDataValidation(isChecked);
					}
				});
		mCacheSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				SimpleWeatherApplication.setDBCahceEnabled(isChecked);

			}
		});
		mMissingDataSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						SimpleWeatherApplication.setMissingData(isChecked);
					}
				});
		mUnitsSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				SimpleWeatherApplication.setUnits((isChecked ? SimpleWeatherApplication.UNITS.US
						: SimpleWeatherApplication.UNITS.SI).ordinal());
			}
		});
	}

}
