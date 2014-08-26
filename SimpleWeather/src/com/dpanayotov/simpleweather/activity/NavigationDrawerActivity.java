package com.dpanayotov.simpleweather.activity;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dpanayotov.simpleweather.R;

public class NavigationDrawerActivity extends Activity {

	private static final int POS_HOME_LOCATION = 0;
	private static final int POS_LIST = 1;
	private static final int POS_MAP = 2;
	private static final int POS_SETTINGS = 3;
	private static final int POS_ABOUT = 4;

	@Override
	public void setContentView(int layoutResID) {
		DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.activity_base_navigation_drawer, null);
		FrameLayout actContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		getLayoutInflater().inflate(layoutResID, actContent, true);

		ListView drawerList = ((ListView) fullLayout
				.findViewById(R.id.left_drawer));
		drawerList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.drawer_items)));
		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
			}
		});
		
		super.setContentView(fullLayout);
	}

	private void selectItem(int position) {
		// TODO #10 Navigation
		switch (position) {
		case POS_HOME_LOCATION:
			Log.d("Menu", "Home Location");
			break;
		case POS_LIST:
			Log.d("Menu", "List");
			break;
		case POS_MAP:
			Log.d("Menu", "Map");
			break;
		case POS_SETTINGS:
			Log.d("Menu", "Settings");
			break;
		case POS_ABOUT:
			Log.d("Menu", "About");
			break;
		default:
			Log.d("Menu", "Default");
			break;
		}
	}
}
