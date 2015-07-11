package com.dpanayotov.simpleweather.activity.forecast.fragment.list;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.api.response.Forecast;
import com.dpanayotov.simpleweather.util.BitmapCirclesUtil;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.WeatherImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastAdapter extends ArrayAdapter<Forecast> {
	private Context mContext;
	private boolean mIsDaily;

	public ForecastAdapter(Context context, List<Forecast> objects, boolean isDaily) {
		super(context, R.layout.layout_forecast_item, objects);
		mContext = context;
		mIsDaily = isDaily;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        Forecast forecast = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = ((LayoutInflater) getContext().getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_forecast_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String formattedTime = DateUtil.getFormatedDate(forecast.getTime(), mIsDaily ?
                DateUtil.FORECAST_LIST_FORMAT_DAILY : DateUtil.FORECAST_LIST_FORMAT_HOURLY);
        holder.midnightBar.setVisibility(!mIsDaily && DateUtil.FORECAST_LIST_MIDNIGHT
                .equals(formattedTime) ? View.VISIBLE : View.GONE);
        holder.item.setBackgroundColor(mContext.getResources().getColor(position % 2 == 0 ?
                R.color.terra_terra : R.color.terra_terra_terra));
        holder.time.setText(formattedTime);
        if (forecast.getIcon() == null && forecast.getSummary() == null) {
            holder.data.setVisibility(View.GONE);
            holder.noData.setVisibility(View.VISIBLE);
        } else {
            holder.data.setVisibility(View.VISIBLE);
            holder.noData.setVisibility(View.GONE);

            holder.icon.setImageResource(WeatherImageUtil.returnImageResource(forecast.getIcon()));
            holder.precip.setImageBitmap(BitmapCirclesUtil.getPrecipCircle(
                    forecast.getPrecipIntensity(), forecast.getPrecipProbability()));
            float temperature;
            if (mIsDaily) {
                temperature = (forecast.getTemperatureMin() + forecast.getTemperatureMax()) / 2;
            } else {
                temperature = forecast.getTemperature();
            }
            holder.temperature.setImageBitmap(BitmapCirclesUtil.getTemperatureCircle(temperature));
            holder.temperatureText.setText("" + (int) temperature);
            holder.clouds.setImageBitmap(BitmapCirclesUtil.getCloudCircle(forecast.getCloudCover()));
            holder.wind.setImageBitmap(BitmapCirclesUtil.getWindCircle(forecast.getWindSpeed()));
        }
        return convertView;
	}

    static class ViewHolder {

        @Bind(R.id.midnight_bar) View midnightBar;
        @Bind(R.id.item) View item;
        @Bind(R.id.time) TextView time;
        @Bind(R.id.data) View data;
        @Bind(R.id.no_data) View noData;
        @Bind(R.id.icon) ImageView icon;
        @Bind(R.id.precip) ImageView precip;
        @Bind(R.id.temperature) ImageView temperature;
        @Bind(R.id.temperature_text) TextView temperatureText;
        @Bind(R.id.clouds) ImageView clouds;
        @Bind(R.id.wind) ImageView wind;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}