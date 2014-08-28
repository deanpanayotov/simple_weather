package com.dpanayotov.simpleweather.activity;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;
import com.dpanayotov.simpleweather.api.request.CurrentForecastParams;
import com.dpanayotov.simpleweather.api.request.CurrentForecastRequest;
import com.dpanayotov.simpleweather.general.RequestManager;

public class MainActivity extends BaseSWActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button) findViewById(R.id.button))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						RequestManager.sendServerRequest(MainActivity.this,
								"test", new CurrentForecastRequest(
										new CurrentForecastParams(42.709197f,
												23.324034f),
										BaseForecastResponse.class,
										MainActivity.this), null);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		String tag_json_obj = "json_obj_req";

		String url = "http://api.androidhive.info/volley/person_object.json";

		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d("test", response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d("test", "Error: " + error.getMessage());
					}
				});

		// ((SimpleWeatherApplication) getApplication()).addToRequestQueue(
		// jsonObjReq, tag_json_obj);
	}

}
