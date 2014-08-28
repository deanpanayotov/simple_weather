package com.dpanayotov.simpleweather.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.adapter.NavigationDrawerArrayAdapter;
import com.dpanayotov.simpleweather.adapter.NavigationDrawerContent;
import com.dpanayotov.simpleweather.util.LogUtil;

public abstract class NavigationDrawerActivity extends ProcessDialogActivity {

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

	@Override
	public void setContentView(View view) {
		DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.activity_base_navigation_drawer, null);
		FrameLayout actContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		actContent.addView(view);
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
		drawerList.setAdapter(new NavigationDrawerArrayAdapter(this,
				NavigationDrawerContent.getContent()));
		drawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
			}
		});
		mDrawerToggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_drawer_terra, R.string.drawer_open,
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
		case NavigationDrawerContent.POS_HOME_LOCATION:
			LogUtil.d("Menu", "Home Location");
			break;
		case NavigationDrawerContent.POS_LIST:
			LogUtil.d("Menu", "List");
			break;
		case NavigationDrawerContent.POS_MAP:
			LogUtil.d("Menu", "Map");
			startActivity(new Intent(this, MapActivity.class));
			break;
		case NavigationDrawerContent.POS_SETTINGS:
			LogUtil.d("Menu", "Settings");
			break;
		case NavigationDrawerContent.POS_ABOUT:
			LogUtil.d("Menu", "About");
			break;
		default:
			LogUtil.d("Menu", "Default");
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
