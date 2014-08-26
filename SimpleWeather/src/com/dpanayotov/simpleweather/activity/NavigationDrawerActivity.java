package com.dpanayotov.simpleweather.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dpanayotov.simpleweather.R;

public abstract class NavigationDrawerActivity extends Activity {

	private static final int POS_HOME_LOCATION = 0;
	private static final int POS_LIST = 1;
	private static final int POS_MAP = 2;
	private static final int POS_SETTINGS = 3;
	private static final int POS_ABOUT = 4;

	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	public void setContentView(int layoutResID) {
		DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.activity_base_navigation_drawer, null);
		FrameLayout actContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		getLayoutInflater().inflate(layoutResID, actContent, true);
		super.setContentView(fullLayout);
		setUpDrawer(fullLayout);
	}

	/**
	 * Sets up the Navigation Drawer
	 * 
	 * @param drawer
	 */
	private void setUpDrawer(DrawerLayout drawer) {
		ListView drawerList = ((ListView) findViewById(R.id.left_drawer));
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
		mDrawerToggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
		};
		drawer.setDrawerListener(mDrawerToggle);
		drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

	}

	/**
	 * Handles the navigation drawer item selection
	 * 
	 * @param position
	 *            position of the pressed button
	 */
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

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
