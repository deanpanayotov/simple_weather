package com.dpanayotov.simpleweather.activity.forecast.fragment.list;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.activity.forecast.IForecastDataProvider;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.Constants;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public abstract class ForecastFragment extends Fragment {

    @Bind(R.id.title_bar) TextView titleBar;
    @Bind(R.id.icon) ImageView icon;
    @Bind(R.id.summary) TextView summary;
    @Bind(R.id.list) ListView list;

    /**
     * @return the resource id of the layout with which the root view is
     *         inflated in
     *         {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
	public abstract int getLayoutResourceId();

	public abstract String getSummaryIcon();

	public abstract String getSummaryText();

	public abstract String getTitleBarText();

	public abstract List<Forecast> getForecastList();

	public abstract boolean isDaily();

	public abstract Class getSingleForecastActivity();

	private ForecastResponse mResponse;

	protected ForecastResponse getResponse() {
		return mResponse;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(this, root);
		return root;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mResponse = ((IForecastDataProvider) getActivity()).getForecastData();

        titleBar.setText(getTitleBarText());
        icon.setImageResource(WeatherImageUtil.returnImageResource(getSummaryIcon()));
        summary.setText(getSummaryText());
        list.setAdapter(new ForecastAdapter(getActivity(), getForecastList(), isDaily()));
	}

    @OnItemClick(R.id.list)
    public void listOnItemClick(long id){
        Intent intent = new Intent(getActivity(), getSingleForecastActivity());
        intent.putExtra(Constants.PARAM_FULL_FORECAST_RESPONSE, mResponse);
        intent.putExtra(Constants.PARAM_FORECAST_ID,(int) id);
        intent.putExtra(Constants.PARAM_LOCATION_NAME, getActivity().getActionBar().getTitle());
        startActivity(intent);
    }
}
