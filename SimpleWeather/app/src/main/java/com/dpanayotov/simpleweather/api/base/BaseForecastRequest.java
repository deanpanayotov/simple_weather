package com.dpanayotov.simpleweather.api.base;

import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;

public abstract class BaseForecastRequest<T> extends CacheGsonGetRequest<T> {

	public BaseForecastRequest(BaseForecastParams params,
			Class<T> responseClass, BaseSWActivity activity) {
		super(params.build(), responseClass, new BaseForecastErrorListener(
				activity, params.build()));
	}
}
