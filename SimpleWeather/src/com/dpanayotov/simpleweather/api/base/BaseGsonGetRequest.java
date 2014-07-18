package com.dpanayotov.simpleweather.api.base;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * A simple {@link Request} wrapper that parses the json response before
 * delivering it to the {@link Listener}
 * 
 * @param <T>
 *            response class
 */
public class BaseGsonGetRequest<T> extends Request<T> {
	private Gson mGson = new Gson();
	private Listener<T> mListener;
	private Class<T> mResponseClass;

	public BaseGsonGetRequest(int method, String url, ErrorListener listener,
			Class<T> responseClass) {
		super(method, url, listener);
		mResponseClass = responseClass;
	}

	@Override
	protected void deliverResponse(T response) {
		if (mListener != null) {
			mListener.onResponse(response);
		}
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		Reader jsonInputStreamReader = new InputStreamReader(
				new ByteArrayInputStream(response.data));
		return Response.success(
				mGson.fromJson(jsonInputStreamReader, mResponseClass),
				HttpHeaderParser.parseCacheHeaders(response));
	}

	/**
	 * Sets the response listener
	 */
	public void setResponseListener(Listener<T> listener) {
		this.mListener = listener;
	}

}
