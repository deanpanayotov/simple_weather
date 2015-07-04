package com.dpanayotov.simpleweather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.activity.forecast.ForecastActivity;
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
		setContentView(R.layout.activity_map);
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
		if (getIntent().getBooleanExtra(Constants.PARAM_NO_LOCATION, false)) {
			Toast.makeText(this, R.string.no_location, Toast.LENGTH_SHORT)
					.show();
		}
	}	
}
