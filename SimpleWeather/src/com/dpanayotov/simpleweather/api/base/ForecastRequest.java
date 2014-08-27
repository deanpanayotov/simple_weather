package com.dpanayotov.simpleweather.api.base;

import android.content.Context;


public class ForecastRequest extends BaseGsonGetRequest<ForecastResponse> {

	public ForecastRequest(String url, Class<ForecastResponse> responseClass,
			Context context) {
		super(url, responseClass, context);
		// TODO Auto-generated constructor stub
	}

}
