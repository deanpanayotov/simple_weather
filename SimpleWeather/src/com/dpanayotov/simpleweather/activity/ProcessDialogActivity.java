package com.dpanayotov.simpleweather.activity;

import android.app.Activity;
import android.os.Bundle;

public class ProcessDialogActivity extends Activity {
	private ProcessDialogFragment mProgressDialogFragment;
	private boolean mIsProgressDialogShown;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mProgressDialogFragment = new ProcessDialogFragment();
	}

	public void showProgressDialog() {
		if (!isFinishing() && !mIsProgressDialogShown) {
			mProgressDialogFragment.show(getFragmentManager(), "");
			mIsProgressDialogShown = true;
		}
	}

	public void hideProgressDialog() {
		if (!isFinishing() && mProgressDialogFragment != null
				&& mIsProgressDialogShown) {
			mProgressDialogFragment.dismissAllowingStateLoss();
			mIsProgressDialogShown = false;
		}
	}
}
