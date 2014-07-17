package com.dpanayotov.simpleweather.general;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class SimpleWeatherApplication extends Application {
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}
		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		if (mImageLoader == null) {
			 mImageLoader = new ImageLoader(getRequestQueue(), new
			 LRUBitmapCache());
		}
		return mImageLoader;
	}
	
	public <T> void addToRequestQueue(Request <T> request, String tag){
		request.setTag(tag);
		getRequestQueue().add(request);
	}
	
	public void cancelRequest(Object tag){
		getRequestQueue().cancelAll(tag);
	}
}
