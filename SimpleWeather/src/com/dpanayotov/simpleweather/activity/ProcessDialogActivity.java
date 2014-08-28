package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ProcessDialogActivity extends FragmentActivity {
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
