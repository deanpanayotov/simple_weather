package com.dpanayotov.simpleweather.activity.base;

import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.util.DateUtil;

public class BaseSWActivity extends NavigationDrawerActivity {

	private boolean mBackPressedOnce;
	private long TAP_TWICE_DELAY = 2 * DateUtil.SECOND;

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

	@Override
	public void onBackPressed() {
		if (doubleTapBack()) {
			if (mBackPressedOnce) {
				super.onBackPressed();
				return;
			}

			this.mBackPressedOnce = true;
			Toast.makeText(this, getString(R.string.tap_back),
					Toast.LENGTH_SHORT).show();

			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					mBackPressedOnce = false;
				}
			}, TAP_TWICE_DELAY);
		} else {
			super.onBackPressed();
		}
	}

	/**
	 * 
	 * @return true if user is able to exit the application on double back tap;
	 *         false otherwise
	 */
	protected boolean doubleTapBack() {
		return true;
	}
}
