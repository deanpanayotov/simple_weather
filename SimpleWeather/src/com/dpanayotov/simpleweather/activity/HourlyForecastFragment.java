package com.dpanayotov.simpleweather.activity;

import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.api.response.ForecastResponse;
import com.dpanayotov.simpleweather.util.LogUtil;

public class HourlyForecastFragment extends Fragment {

	private GraphicalView mChartView;
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	private TimeSeries mTemperatureSeries;
	private XYSeriesRenderer mCurrentRenderer;

	// private LinearLayout mChart;

	private void initChart(ForecastResponse response) {
		mTemperatureSeries = new TimeSeries("Temperature");
		int i = 0;
		for (Forecast forecast : response.getHourly().getData()) {
			mTemperatureSeries.add(new Date(forecast.getTime()*1000),
					forecast.getTemperature());

			// if (i > 11) {
			// break;
			// }
		}
		XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
		mDataset.addSeries(mTemperatureSeries);
		mCurrentRenderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(mCurrentRenderer);
		mChartView = ChartFactory.getTimeChartView(getActivity(), mDataset,
				mRenderer, "HH:mm");
		// mChartView = ChartFactory.getCubeLineChartView(getActivity(),
		// mDataset,
		// mRenderer, 0.3f);
		((LinearLayout) getView().findViewById(R.id.chart)).addView(mChartView);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_hourly_forecast,
				container, false);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		populateChart(((IForecastDataProvider) getActivity()).getForecastData());
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void populateChart(ForecastResponse response) {
		if (mChartView == null) {
			initChart(response);
		} else {
			mChartView.repaint();
		}
	}

}
