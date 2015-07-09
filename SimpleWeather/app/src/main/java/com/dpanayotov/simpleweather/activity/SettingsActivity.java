package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseSWActivity {

	@Bind(R.id.switch_validation) Switch mValidationSwitch;
	@Bind(R.id.switch_cache) Switch mCacheSwitch;
	@Bind(R.id.switch_missing_data) Switch mMissingDataSwitch;
	@Bind(R.id.switch_units) Switch mUnitsSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ButterKnife.bind(this);
		setControlsState();
	}

	private void setControlsState() {
		mValidationSwitch.setChecked(SimpleWeatherApplication.isDataValidationEnabled());
		mCacheSwitch.setChecked(SimpleWeatherApplication.isDBCahceEnabled());
		mMissingDataSwitch.setChecked(SimpleWeatherApplication.isMissingDataEnabled());
		mUnitsSwitch
				.setChecked(SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US);
	}

	@OnClick(R.id.switch_validation)
	public void validationSwitchOnClick(Switch switchView){
		SimpleWeatherApplication.setDataValidation(switchView.isChecked());
	}
	@OnClick(R.id.switch_cache)
	public void cacheSwitchOnClick(Switch switchView){
		SimpleWeatherApplication.setDBCahceEnabled(switchView.isChecked());
	}
	@OnClick(R.id.switch_missing_data)
	public void missingDataSwitchOnClick(Switch switchView){
		SimpleWeatherApplication.setMissingData(switchView.isChecked());
	}
	@OnClick(R.id.switch_units)
	public void unitsSwitchOnClick(Switch switchView){
		SimpleWeatherApplication.setUnits((switchView.isChecked() ? SimpleWeatherApplication.UNITS.US
				: SimpleWeatherApplication.UNITS.SI).ordinal());
	}
}
