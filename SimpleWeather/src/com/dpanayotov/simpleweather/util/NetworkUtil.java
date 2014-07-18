package com.dpanayotov.simpleweather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	/**
	 * Check for internet connection.
	 * 
	 * @param context
	 *            - a {@link Context}
	 * @return true, if the device is connected to a network, false otherwise
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
}
