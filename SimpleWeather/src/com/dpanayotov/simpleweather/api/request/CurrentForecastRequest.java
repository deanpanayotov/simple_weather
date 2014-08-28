package com.dpanayotov.simpleweather.api.request;

import android.content.Context;

import com.dpanayotov.simpleweather.api.base.BaseForecastRequest;
import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;

public class CurrentForecastRequest extends BaseForecastRequest {

	public CurrentForecastRequest(CurrentForecastParams params,
			Class<BaseForecastResponse> responseClass, Context context) {
		super(params, BaseForecastResponse.class, context);
	}

}
