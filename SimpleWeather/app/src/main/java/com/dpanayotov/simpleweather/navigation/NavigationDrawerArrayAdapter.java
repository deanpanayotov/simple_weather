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

import butterknife.Bind;
import butterknife.ButterKnife;

public class NavigationDrawerArrayAdapter extends
		ArrayAdapter<NavigationDrawerItem> {
	Context mContext;

	public NavigationDrawerArrayAdapter(Context context,
			List<NavigationDrawerItem> objects) {
		super(context, R.layout.layout_navigation_drawer_item, objects);
		mContext = context;
	}

	static class ViewHolder {
		@Bind(R.id.text) TextView title;
        @Bind(R.id.image) ImageView image;


        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		NavigationDrawerItem rowItem = getItem(position);

		if (convertView == null) {
			convertView = ((LayoutInflater) mContext.
                    getSystemService(Activity.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_navigation_drawer_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(rowItem.getTextResourceId());
		holder.image.setImageResource(rowItem.getImageResourceId());

		return convertView;
	}

}
