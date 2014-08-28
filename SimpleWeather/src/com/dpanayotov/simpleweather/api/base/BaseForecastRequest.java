package com.dpanayotov.simpleweather.api.base;

import android.content.Context;

public class BaseForecastRequest<T> extends BaseGsonGetRequest<T> {

	public BaseForecastRequest(BaseForecastParams params,
			Class<T> responseClass, Context context) {
		super(params.build(), responseClass, context);
	}

}
