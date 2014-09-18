package com.dpanayotov.simpleweather.api.base;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
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
			BaseSWActivity activity) {
		super(Method.GET, url, new BaseForecastErrorListener(activity, url));
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
		onNetworkResponseReceived(response);
		Reader jsonInputStreamReader = new InputStreamReader(
				new ByteArrayInputStream(response.data));
		return Response.success(
				SimpleWeatherApplication.getGson().fromJson(
						jsonInputStreamReader, mResponseClass),
				HttpHeaderParser.parseCacheHeaders(response));
	}

	/**
	 * Handles any additional logic before parsing the {@link NetworkResponse}
	 * 
	 * @param response
	 */
	protected void onNetworkResponseReceived(NetworkResponse response) {
		if (LogUtil.NETWORKING_DEBUG_ENABLED) {
			printResponse(response);
		}
	}

	private void printResponse(NetworkResponse response) {
		StringBuilder sb = new StringBuilder();
		sb.append("=============[RESPONSE]=============");
		sb.append("\n");
		sb.append("URL: ");
		sb.append(mUrl);
		sb.append("\n");
		sb.append("timestamp: ");
		sb.append(DateUtil.getFormatedDate(mTimestamp,
				DateUtil.NETWORKING_DEBUG_TIMESTAMP_FORMAT));
		sb.append("\n");
		sb.append("------------------------------------");
		sb.append("\n");
		LogUtil.n(sb.toString());
		String responseString = new String(response.data);
		if (LogUtil.JSON_PRETTY_PRINT_ENABLED) {
			try {
				responseString = new JSONObject(responseString).toString(2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		int length = responseString.length();

		for (int i = 0; i < length; i += 1024) {
			if (i + 1024 < length)
				LogUtil.n(responseString.substring(i, i + 1024));
			else
				LogUtil.n(responseString.substring(i, length));
		}
		sb.append(responseString);
		LogUtil.n("\n");
		LogUtil.n("------------------------------------");
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
