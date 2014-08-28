package com.dpanayotov.simpleweather.activity;

import android.content.Intent;
import android.os.Bundle;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.util.Constants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends BaseSWActivity {
	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_test);
		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public void onMapClick(LatLng latlng) {
				Intent intent = new Intent(MapActivity.this,
						ForecastActivity.class);
				Bundle extras = new Bundle();
				extras.putParcelable(Constants.PARAM_LATLNG, latlng);
				intent.putExtras(extras);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
