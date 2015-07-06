package com.dpanayotov.simpleweather.general;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.api.base.BaseGsonGetRequest;
import com.dpanayotov.simpleweather.api.base.CacheGsonGetRequest;
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
			Object tag, BaseGsonGetRequest<T> request,
			final Listener<T> listener) {
		if (SimpleWeatherApplication.isDBCahceEnabled()
				&& (request instanceof CacheGsonGetRequest)) {
			T response = ((CacheGsonGetRequest<T>) request).checkInCache();
			if (response != null) {
				activity.hideProgressDialog();
				if (listener != null) {
					LogUtil.d(LogUtil.CACHE_TAG, "cached response aquired!");
					listener.onResponse(response);
				}
				return;
			}
		}
		request.setResponseListener(new Listener<T>() {

			@Override
			public void onResponse(T response) {
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

	private static void printRequestToBeSent(BaseGsonGetRequest<?> request) {
		LogUtil.n(
				"=============[REQUEST]=============\n" +
				"URL: " + request.getUrl() + "\n" +
				"timestamp: " + DateUtil.getFormatedDate(request.getmTimestamp(),
				DateUtil.NETWORKING_DEBUG_TIMESTAMP_FORMAT) + "\n" +
				"------------------------------------\n"
		);
	}
}
