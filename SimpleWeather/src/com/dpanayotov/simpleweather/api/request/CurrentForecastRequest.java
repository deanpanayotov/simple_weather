package com.dpanayotov.simpleweather.api.request;

import com.android.volley.NetworkResponse;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.api.base.BaseForecastRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;
import com.dpanayotov.simpleweather.util.LogUtil;

public class CurrentForecastRequest extends
		BaseForecastRequest<ForecastResponse> {
	private String mLocation;

	public CurrentForecastRequest(CurrentForecastParams params,
			BaseSWActivity activity, String location) {
		super(params, ForecastResponse.class, activity);
		mLocation = location;
	}

	@Override
	protected void onNetworkResponseReceived(NetworkResponse response) {
		super.onNetworkResponseReceived(response);
		SimpleWeatherApplication.getCache().insertResponse(mLocation,
				System.currentTimeMillis(), new String(response.data));
	}

	@Override
	public void cacheResponse(NetworkResponse response) {
		SimpleWeatherApplication.getCache().insertResponse(mLocation,
				System.currentTimeMillis(), new String(response.data));
		LogUtil.d(LogUtil.CACHE_TAG, "response cached");
	}

	@Override
	public ForecastResponse checkInCache() {
		String json = SimpleWeatherApplication.getCache()
				.getResponse(mLocation);
		if (json != null && json.length() > 0) {
			return SimpleWeatherApplication.getGson().fromJson(json,
					ForecastResponse.class);
		}
		return null;
	}
}
