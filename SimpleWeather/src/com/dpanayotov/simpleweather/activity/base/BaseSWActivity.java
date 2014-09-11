package com.dpanayotov.simpleweather.activity.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dpanayotov.simpleweather.R;

public class BaseSWActivity extends NavigationDrawerActivity {
	@Override
	public void setContentView(int layoutResID) {
		LinearLayout fullLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_sw_base, null);
		FrameLayout actContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		getLayoutInflater().inflate(layoutResID, actContent, true);
		super.setContentView(fullLayout);
	}

	public void setContentView(View view) {
		LinearLayout fullLayout = (LinearLayout) getLayoutInflater().inflate(
				R.layout.activity_sw_base, null);
		FrameLayout actContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		actContent.addView(view);
		super.setContentView(fullLayout);
	}
}
