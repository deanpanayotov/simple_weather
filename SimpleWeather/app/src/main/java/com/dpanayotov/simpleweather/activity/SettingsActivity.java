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
	SimpleWeatherApplication app;

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
		app = SimpleWeatherApplication.getInstance();
	}

	private void setControlsState() {
		mValidationSwitch.setChecked(app.isDataValidationEnabled());
		mCacheSwitch.setChecked(app.isDBCahceEnabled());
		mMissingDataSwitch.setChecked(app.isMissingDataEnabled());
		mUnitsSwitch
				.setChecked(app.getUnits() == SimpleWeatherApplication.UNITS.US);
	}

	private void setOnClickListeners() {
		mValidationSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						app.setDataValidation(isChecked);
					}
				});
		mCacheSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				app.setDBCahceEnabled(isChecked);

			}
		});
		mMissingDataSwitch
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						app.setMissingData(isChecked);
					}
				});
		mUnitsSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				app.setUnits((isChecked ? SimpleWeatherApplication.UNITS.US
						: SimpleWeatherApplication.UNITS.SI).ordinal());
			}
		});
	}

}
