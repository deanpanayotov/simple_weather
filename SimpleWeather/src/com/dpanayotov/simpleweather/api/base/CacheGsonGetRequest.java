package com.dpanayotov.simpleweather.api.base;

import com.android.volley.NetworkResponse;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;

public abstract class CacheGsonGetRequest<T> extends BaseGsonGetRequest<T> {

	public CacheGsonGetRequest(String url, Class<T> responseClass,
			BaseSWActivity activity) {
		super(url, responseClass, activity);
	}

	@Override
	protected void onNetworkResponseReceived(NetworkResponse response) {
		super.onNetworkResponseReceived(response);
	}

	public abstract void cacheResponse(NetworkResponse response);

	public abstract T checkInCache();

}
