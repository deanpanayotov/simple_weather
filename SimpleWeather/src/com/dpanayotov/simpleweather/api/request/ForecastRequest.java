package com.dpanayotov.simpleweather.api.request;

import android.content.Context;

import com.dpanayotov.simpleweather.api.base.BaseGsonGetRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;

public class ForecastRequest extends BaseGsonGetRequest<ForecastResponse>{

	public ForecastRequest(int method, String url,
			Class<ForecastResponse> responseClass, Context context) {
		super(method, url, responseClass, context);
		// TODO Auto-generated constructor stub
	}

}
