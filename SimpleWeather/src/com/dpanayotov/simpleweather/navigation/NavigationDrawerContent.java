package com.dpanayotov.simpleweather.navigation;

import java.util.ArrayList;
import java.util.List;

import com.dpanayotov.simpleweather.R;

public class NavigationDrawerContent {
	public static final int POS_MY_LOCATION = 0;
	public static final int POS_MAP = 1;
	public static final int POS_SETTINGS = 2;
	public static final int POS_ABOUT = 3;

	public static final List<NavigationDrawerItem> getContent() {
		List<NavigationDrawerItem> content = new ArrayList<NavigationDrawerItem>();
		content.add(new NavigationDrawerItem(R.drawable.ic_my_location_terra,
				R.string.drawer_option_my_location));
		content.add(new NavigationDrawerItem(R.drawable.ic_map_terra,
				R.string.drawer_option_map));
		content.add(new NavigationDrawerItem(R.drawable.ic_settings_terra,
				R.string.drawer_option_settings));
		content.add(new NavigationDrawerItem(R.drawable.ic_about_terra,
				R.string.drawer_option_about));
		return content;
	}
}
