package com.dpanayotov.simpleweather.adapter;

import java.util.ArrayList;
import java.util.List;

import com.dpanayotov.simpleweather.R;

public class NavigationDrawerContent {
	public static final int POS_HOME_LOCATION = 0;
	public static final int POS_LIST = 1;
	public static final int POS_MAP = 2;
	public static final int POS_SETTINGS = 3;
	public static final int POS_ABOUT = 4;

	public static final List<NavigationDrawerItem> getContent() {
		List<NavigationDrawerItem> content = new ArrayList<NavigationDrawerItem>();
		content.add(new NavigationDrawerItem(R.drawable.ic_home_location_terra,
				R.string.drawer_option_home_location));
		content.add(new NavigationDrawerItem(R.drawable.ic_list_terra,
				R.string.drawer_option_list));
		content.add(new NavigationDrawerItem(R.drawable.ic_map_terra,
				R.string.drawer_option_map));
		content.add(new NavigationDrawerItem(R.drawable.ic_settings_terra,
				R.string.drawer_option_settings));
		content.add(new NavigationDrawerItem(R.drawable.ic_about_terra,
				R.string.drawer_option_about));
		return content;
	}
}
