package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication.UNITS;

public class SettingsActivity extends BaseSWActivity {
	Switch mValidationSwitch;
	Switch mCacheSwitch;
	Switch mMissingDataSwitch;
	Spinner mUnitSpinner;
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
		mUnitSpinner = (Spinner) findViewById(R.id.spinner_units);
		app = SimpleWeatherApplication.getInstance();
	}

	private void setControlsState() {
		mValidationSwitch.setChecked(app.isDataValidationEnabled());
		mCacheSwitch.setChecked(app.isDBCahceEnabled());
		mMissingDataSwitch.setChecked(app.isMissingDataEnabled());
		mUnitSpinner.setAdapter(new ArrayAdapter<UNITS>(this,
				android.R.layout.simple_spinner_item,
				SimpleWeatherApplication.UNITS.values()));
		mUnitSpinner
				.setSelection(SimpleWeatherApplication.getUnits().ordinal());
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
		mUnitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				app.setUnits((int) id);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

}
