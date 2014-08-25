package com.dpanayotov.simpleweather.activity;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.R.id;
import com.dpanayotov.simpleweather.R.layout;
import com.dpanayotov.simpleweather.R.menu;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    @Override
    protected void onResume() {
    	super.onResume();
    	String tag_json_obj = "json_obj_req";
    	 
    	String url = "http://api.androidhive.info/volley/person_object.json";    
    	         
    	        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
    	                url, null,
    	                new Response.Listener<JSONObject>() {
    	 
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
    	 
    	((SimpleWeatherApplication)getApplication()).addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}
