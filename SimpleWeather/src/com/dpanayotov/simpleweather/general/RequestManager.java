package com.dpanayotov.simpleweather.general;

import android.text.format.DateFormat;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.dpanayotov.simpleweather.activity.BaseSWActivity;
import com.dpanayotov.simpleweather.api.base.BaseGsonGetRequest;
import com.dpanayotov.simpleweather.api.base.BaseForecastRequest;
import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.LogUtil;

public class RequestManager {
	private static final String TAG = "RequestManager";
	private static RequestQueue mRequestQueue;

	/**
	 * @return a volley request queue to handle networking requests
	 */
	public static RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(SimpleWeatherApplication
					.getContext());
		}

		return mRequestQueue;
	}

	public static <T> void sendServerRequest(final BaseSWActivity activity,
			Object tag, BaseForecastRequest request,
			final Listener<BaseForecastResponse> listener) {

		request.setResponseListener(new Listener<BaseForecastResponse>() {

			@Override
			public void onResponse(BaseForecastResponse response) {
				activity.hideProgressDialog();
				if (response == null) {
					LogUtil.e(TAG, "The response is null!!!");
					return;
				}
				if (listener != null) {
					listener.onResponse(response);
				}
			}

		});

		request.setTag(tag);
		if (LogUtil.NETWORKING_DEBUG_ENABLED) {
			printRequestToBeSent(request);
		}
		getRequestQueue().add(request);
		activity.showProgressDialog();
	}

	private static void printRequestToBeSent(BaseForecastRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append("=============[REQUEST]=============");
		sb.append("\n");
		sb.append("URL: ");
		sb.append(request.getUrl());
		sb.append("\n");
		sb.append("timestamp: ");
		sb.append(DateFormat.format(DateUtil.NETWORKING_DEBUG_TIMESTAMP_FORMAT,
				request.getmTimestamp()));
		sb.append("\n");
		sb.append("------------------------------------");

		LogUtil.n(sb.toString());

	}
}
