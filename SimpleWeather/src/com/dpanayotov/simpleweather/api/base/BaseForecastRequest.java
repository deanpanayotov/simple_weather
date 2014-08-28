package com.dpanayotov.simpleweather.api.base;

import android.content.Context;

public class BaseForecastRequest extends BaseGsonGetRequest<BaseForecastResponse> {

	public BaseForecastRequest(BaseForecastParams params, Class<BaseForecastResponse> responseClass,
			Context context) {
		super(params.build(), responseClass, context);
	}

}
