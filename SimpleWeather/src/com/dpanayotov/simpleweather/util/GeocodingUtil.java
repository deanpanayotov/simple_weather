package com.dpanayotov.simpleweather.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;
import com.google.android.gms.maps.model.LatLng;

public class GeocodingUtil {

	public static String getGeocodeName(LatLng latlng) {
		return getGeocodeName(latlng.latitude, latlng.longitude);
	}

	public static String getGeocodeName(double latitude, double longitude) {
		Context context = SimpleWeatherApplication.getContext();
		Geocoder geocoder = new Geocoder(context);
		List<Address> addresses = null;
		String unknown = context.getString(R.string.unknown_location);
		try {
			addresses = geocoder.getFromLocation(latitude, longitude, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return unknown;
		}
		if (addresses == null || addresses.size() == 0) {
			return unknown;
		}
		Address address = addresses.get(0);
		String cn = address.getCountryName();
		if (empty(cn)) {
			return unknown;
		}
		unknown += ", " + cn;
		String mainLocality = address.getLocality();
		if (empty(mainLocality)) {
			mainLocality = address.getSubAdminArea();
			if (empty(mainLocality)) {
				mainLocality = address.getAdminArea();
				if (empty(mainLocality)) {
					return unknown;
				}
			}
		}
		return mainLocality + ", " + cn;
	}

	private static boolean empty(String str) {
		return str == null || str.length() == 0;
	}
}
