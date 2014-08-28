package com.dpanayotov.simpleweather.activity;

import com.dpanayotov.simpleweather.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HourlyForecastFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_hourly_forecast,
				container, false);
		return root;

	}
}
