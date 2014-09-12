package com.dpanayotov.simpleweather.api.request;

import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.api.base.BaseForecastRequest;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;

public class CurrentForecastRequest extends
		BaseForecastRequest<ForecastResponse> {

	public CurrentForecastRequest(CurrentForecastParams params, BaseSWActivity activity) {
		super(params, ForecastResponse.class, activity);
	}

}
