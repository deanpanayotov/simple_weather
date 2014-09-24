package com.dpanayotov.simpleweather.api.base;

import com.android.volley.NetworkResponse;
import com.android.volley.Response.ErrorListener;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

public abstract class CacheGsonGetRequest<T> extends BaseGsonGetRequest<T> {

	public CacheGsonGetRequest(String url, Class<T> responseClass,
			ErrorListener errorListener) {
		super(url, responseClass, errorListener);
	}

	@Override
	protected void onNetworkResponseReceived(NetworkResponse response) {
		super.onNetworkResponseReceived(response);
		if (SimpleWeatherApplication.isDBCahceEnabled()) {
			cacheResponse(response);
		}
	}

	public abstract void cacheResponse(NetworkResponse response);

	public abstract T checkInCache();

}
