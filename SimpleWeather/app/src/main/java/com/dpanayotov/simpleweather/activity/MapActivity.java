package com.dpanayotov.simpleweather.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.base.BaseSWActivity;
import com.dpanayotov.simpleweather.activity.forecast.ForecastActivity;
import com.dpanayotov.simpleweather.util.Constants;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends BaseSWActivity implements OnMapReadyCallback {

	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}


	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
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


		LatLng myPosition;


		if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		googleMap.setMyLocationEnabled(true);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);


		if (location != null) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			LatLng latLng = new LatLng(latitude, longitude);
			myPosition = new LatLng(latitude, longitude);


			LatLng coordinate = new LatLng(latitude, longitude);
			CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 19);
			mMap.animateCamera(yourLocation);
		}
	}
}
