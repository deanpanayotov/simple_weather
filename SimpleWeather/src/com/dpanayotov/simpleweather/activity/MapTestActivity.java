package com.dpanayotov.simpleweather.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dpanayotov.simpleweather.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapTestActivity extends FragmentActivity {
	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_test);
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
				R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng arg0) {
				 
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
