package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dpanayotov.simpleweather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapTestActivity extends FragmentActivity {
	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_test);
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
