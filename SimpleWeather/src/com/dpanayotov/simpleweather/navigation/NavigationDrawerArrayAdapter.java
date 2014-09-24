package com.dpanayotov.simpleweather.navigation;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;

public class NavigationDrawerArrayAdapter extends
		ArrayAdapter<NavigationDrawerItem> {
	Context mContext;

	public NavigationDrawerArrayAdapter(Context context,
			List<NavigationDrawerItem> objects) {
		super(context, R.layout.layout_navigation_drawer_item, objects);
		mContext = context;
	}

	/**
	 * private view holder class
	 */
	private class ViewHolder {
		ImageView imageView;
		TextView txtTitle;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		NavigationDrawerItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) mContext
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.layout_navigation_drawer_item, null);
			holder = new ViewHolder();
			holder.txtTitle = (TextView) convertView.findViewById(R.id.text);
			holder.imageView = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText(rowItem.getTextResourceId());
		holder.imageView.setImageResource(rowItem.getImageResourceId());

		return convertView;
	}

}
