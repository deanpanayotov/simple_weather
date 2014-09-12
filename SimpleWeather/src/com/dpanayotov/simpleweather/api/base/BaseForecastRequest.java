package com.dpanayotov.simpleweather.api.base;

import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;

public class BaseForecastRequest<T> extends BaseGsonGetRequest<T> {

	public BaseForecastRequest(BaseForecastParams params,
			Class<T> responseClass, BaseSWActivity activity) {
		super(params.build(), responseClass, activity);
	}

}
