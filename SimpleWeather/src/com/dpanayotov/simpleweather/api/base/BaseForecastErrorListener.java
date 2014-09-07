package com.dpanayotov.simpleweather.api.base;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.util.DialogUtil;
import com.dpanayotov.simpleweather.util.NetworkUtil;

public class BaseForecastErrorListener implements ErrorListener {
	private Context mContext;
	private String mUrl;

	public BaseForecastErrorListener(Context context, String url) {
		mContext = context;
		mUrl = url;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		if (NetworkUtil.isNetworkConnected(mContext)) {
			handleServerError(error);
		} else {
			DialogUtil.showNeutralAlertDialog(mContext,
					R.string.alert_title_no_internet,
					R.string.aler_message_no_internet, null);
		}
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
		append(sb, mUrl, mContext.getString(R.string.error_message));
		append(sb, message, mContext.getString(R.string.error_message));
		append(sb, cause, mContext.getString(R.string.error_cause));
		append(sb, data, mContext.getString(R.string.error_data));

		DialogUtil.showNeutralAlertDialog(mContext,
				mContext.getString(R.string.error_status_code) + ": "
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
