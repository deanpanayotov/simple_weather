package com.dpanayotov.simpleweather.api.base;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import android.content.Context;
import android.text.format.DateFormat;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.LogUtil;

/**
 * A simple {@link Request} wrapper that parses the json response before
 * delivering it to the {@link Listener}
 * 
 * @param <T>
 *            response class
 */
public class BaseGsonGetRequest<T> extends Request<T> {
	private Listener<T> mListener;
	private Class<T> mResponseClass;
	private String mUrl;
	private long mTimestamp;

	public BaseGsonGetRequest(String url, Class<T> responseClass,
			Context context) {
		super(Method.GET, url, new BaseForecastErrorListener(context, url));
		mResponseClass = responseClass;
		mUrl = url;
		mTimestamp = System.currentTimeMillis();
	}

	@Override
	protected void deliverResponse(T response) {
		if (mListener != null) {
			mListener.onResponse(response);
		}
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		if (LogUtil.NETWORKING_DEBUG_ENABLED) {
			printResponse(response);
		}
		Reader jsonInputStreamReader = new InputStreamReader(
				new ByteArrayInputStream(response.data));
		return Response.success(
				SimpleWeatherApplication.getGson().fromJson(
						jsonInputStreamReader, mResponseClass),
				HttpHeaderParser.parseCacheHeaders(response));
	}

	private void printResponse(NetworkResponse response) {
		StringBuilder sb = new StringBuilder();
		sb.append("=============[RESPONSE]=============");
		sb.append("\n");
		sb.append("URL: ");
		sb.append(mUrl);
		sb.append("\n");
		sb.append("timestamp: ");
		sb.append(DateFormat.format(DateUtil.NETWORKING_DEBUG_TIMESTAMP_FORMAT,
				mTimestamp));
		sb.append("\n");
		sb.append("------------------------------------");
		sb.append("\n");
		sb.append(new String(response.data));
		sb.append("\n");
		sb.append("------------------------------------");

		LogUtil.n(sb.toString());
	}

	/**
	 * Sets the response listener
	 */
	public void setResponseListener(Listener<T> listener) {
		this.mListener = listener;
	}

	public long getmTimestamp() {
		return mTimestamp;
	}

	public void setmTimestamp(long mTimestamp) {
		this.mTimestamp = mTimestamp;
	}

}
