package com.dpanayotov.simpleweather.activity.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.AboutActivity;
import com.dpanayotov.simpleweather.activity.MapActivity;
import com.dpanayotov.simpleweather.activity.SettingsActivity;
import com.dpanayotov.simpleweather.activity.forecast.CurrentLocationForecastActivity;
import com.dpanayotov.simpleweather.activity.forecast.ForecastActivity;
import com.dpanayotov.simpleweather.navigation.NavigationDrawerArrayAdapter;
import com.dpanayotov.simpleweather.navigation.NavigationDrawerContent;

public abstract class NavigationDrawerActivity extends ProcessDialogActivity {

	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawer;

	@Override
	public void setContentView(int layoutResID) {
		DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.activity_base_navigation_drawer, null);
		FrameLayout actualContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		getLayoutInflater().inflate(layoutResID, actualContent, true);
		super.setContentView(fullLayout);
		setUpDrawer(fullLayout);
	}

	@Override
	public void setContentView(View view) {
		DrawerLayout fullLayout = (DrawerLayout) getLayoutInflater().inflate(
				R.layout.activity_base_navigation_drawer, null);
		FrameLayout actualContent = (FrameLayout) fullLayout
				.findViewById(R.id.content_frame);
		actualContent.addView(view);
		super.setContentView(fullLayout);
		setUpDrawer(fullLayout);
	}

	/**
	 * Sets up the Navigation Drawer
	 * 
	 * @param drawerLayout
	 */
	private void setUpDrawer(DrawerLayout drawerLayout) {
		mDrawer = drawerLayout;
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
		mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
		};
		drawerLayout.setDrawerListener(mDrawerToggle);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
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
		case NavigationDrawerContent.POS_MY_LOCATION:
			startActivityClearStack(CurrentLocationForecastActivity.class);
			break;
		case NavigationDrawerContent.POS_MAP:
			startActivityClearStack(MapActivity.class);
			break;
		case NavigationDrawerContent.POS_SETTINGS:
			startActivityClearStack(SettingsActivity.class);
			break;
		case NavigationDrawerContent.POS_ABOUT:
			startActivityClearStack(AboutActivity.class);
			break;
		default:
			startActivityClearStack(ForecastActivity.class);
			break;
		}
	}

	private void startActivityClearStack(Class activityClass) {
		if (this.getClass().equals(activityClass)) {
			mDrawer.closeDrawer(GravityCompat.START);
			return;
		}
		Intent intent = new Intent(this, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
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
		return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
	}

}
