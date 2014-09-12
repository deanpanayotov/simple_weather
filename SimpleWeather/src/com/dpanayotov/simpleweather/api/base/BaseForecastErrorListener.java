package com.dpanayotov.simpleweather.api.base;

import android.app.Activity;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.util.DialogUtil;
import com.dpanayotov.simpleweather.util.NetworkUtil;

public class BaseForecastErrorListener implements ErrorListener {
	private BaseSWActivity mActivity;
	private String mUrl;

	public BaseForecastErrorListener(BaseSWActivity activity, String url) {
		mActivity = activity;
		mUrl = url;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		if (NetworkUtil.isNetworkConnected(mActivity)) {
			handleServerError(error);
		} else {
			NetworkUtil.showNetworkDisabledDialog(mActivity);
		}
		mActivity.hideProgressDialog();
	}

	/**
	 * Simply displays the error in a dialog
	 * 
	 * @param error
	 */
	private void handleServerError(VolleyError error) {
		String message = error.getMessage();
		String cause = null;
		if (error.getCause() != null) {
			cause = error.getCause().getMessage();
		}
		int statusCode = error.networkResponse.statusCode;
		String data = new String(error.networkResponse.data);

		StringBuilder sb = new StringBuilder();
		append(sb, mUrl, mActivity.getString(R.string.error_message));
		append(sb, message, mActivity.getString(R.string.error_message));
		append(sb, cause, mActivity.getString(R.string.error_cause));
		append(sb, data, mActivity.getString(R.string.error_data));

		DialogUtil.showNeutralAlertDialog(mActivity,
				mActivity.getString(R.string.error_status_code) + ": "
						+ statusCode, sb.toString(), null);
	}

	/**
	 * Appends the {@link StringBuilder} with the prefix and the passed string
	 * if the string is not empty; appends a new line at the end
	 * 
	 * @param sb
	 *            {@link StringBuilder} to append
	 * @param string
	 *            the string to be appended
	 * @param prefix
	 *            the prefix infront of the string
	 */
	private void append(StringBuilder sb, String string, String prefix) {
		if (string != null && string.length() > 0) {
			sb.append(prefix);
			sb.append(": ");
			sb.append(string);
			sb.append("\n");
		}
	}
}
