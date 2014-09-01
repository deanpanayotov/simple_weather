package com.dpanayotov.simpleweather.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;
import com.google.gson.annotations.SerializedName;

public class ForecastResponse extends BaseForecastResponse implements
		Parcelable {
	@SerializedName("currently")
	private Forecast currently;
	@SerializedName("hourly")
	private ForecastArray hourly;
	@SerializedName("daily")
	private ForecastArray daily;

	public Forecast getCurrently() {
		return currently;
	}

	public ForecastArray getHourly() {
		return hourly;
	}

	public ForecastArray getDaily() {
		return daily;
	}

	@Override
	public String toString() {
		return "ForecastResponse [currently=" + currently + ", hourly="
				+ hourly + ", daily=" + daily + "]";
	}

	protected ForecastResponse(Parcel in) {
		currently = (Forecast) in.readValue(Forecast.class.getClassLoader());
		hourly = (ForecastArray) in.readValue(ForecastArray.class
				.getClassLoader());
		daily = (ForecastArray) in.readValue(ForecastArray.class
				.getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(currently);
		dest.writeValue(hourly);
		dest.writeValue(daily);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<ForecastResponse> CREATOR = new Parcelable.Creator<ForecastResponse>() {
		@Override
		public ForecastResponse createFromParcel(Parcel in) {
			return new ForecastResponse(in);
		}

		@Override
		public ForecastResponse[] newArray(int size) {
			return new ForecastResponse[size];
		}
	};
	
	public final void selfValidate(){
		
	}
}