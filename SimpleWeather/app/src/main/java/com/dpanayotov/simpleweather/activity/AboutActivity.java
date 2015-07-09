package com.dpanayotov.simpleweather.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.util.DateUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseSWActivity {

	@Bind(R.id.version) TextView version;
	@Bind(R.id.version_code) TextView versionCode;
	@Bind(R.id.installation_time) TextView installationTime;
	@Bind(R.id.last_update_time) TextView lastUpdateTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		ButterKnife.bind(this);

		PackageInfo packageInfo = null;
		try {
			packageInfo = getPackageManager().getPackageInfo(getPackageName(),
					0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (packageInfo != null) {
			version.setText(getString(R.string.version)
					+ packageInfo.versionName);
			versionCode.setText(getString(R.string.version_code)
					+ packageInfo.versionCode);
			installationTime.setText(getString(R.string.installed)
					+ DateUtil.getFormatedDate(packageInfo.firstInstallTime,
							DateUtil.INSTALLATION_FORMAT));
			lastUpdateTime.setText(getString(R.string.last_updated)
					+ DateUtil.getFormatedDate(packageInfo.lastUpdateTime,
							DateUtil.INSTALLATION_FORMAT));
		} else {
			version.setVisibility(View.GONE);
			versionCode.setVisibility(View.GONE);
			installationTime.setVisibility(View.GONE);
			lastUpdateTime.setVisibility(View.GONE);
		}

	}
}
