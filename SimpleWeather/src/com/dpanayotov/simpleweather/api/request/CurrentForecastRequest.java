package com.dpanayotov.simpleweather.api.request;

import android.content.Context;

import com.dpanayotov.simpleweather.api.base.BaseForecastRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;

public class CurrentForecastRequest extends
		BaseForecastRequest<ForecastResponse> {

	public CurrentForecastRequest(CurrentForecastParams params, Context context) {
		super(params, ForecastResponse.class, context);
	}

}
