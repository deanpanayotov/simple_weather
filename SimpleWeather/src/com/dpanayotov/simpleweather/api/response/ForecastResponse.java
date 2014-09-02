package com.dpanayotov.simpleweather.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.dpanayotov.simpleweather.api.base.BaseForecastResponse;
import com.dpanayotov.simpleweather.util.DateUtil;
import com.dpanayotov.simpleweather.util.LogUtil;
import com.google.gson.annotations.SerializedName;

public class ForecastResponse extends BaseForecastResponse implements
		Parcelable {

	private static final int ENTRIES_COUNT_HOURLY = 49;
	private static final int ENTRIES_COUNT_DAILY = 8;

	@SerializedName("offset")
	private int offset;
	@SerializedName("currently")
	private Forecast currently;
	@SerializedName("hourly")
	private ForecastArray hourly;
	@SerializedName("daily")
	private ForecastArray daily;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Forecast getCurrently() {
		return currently;
	}

	public void setCurrently(Forecast currently) {
		this.currently = currently;
	}

	public ForecastArray getHourly() {
		return hourly;
	}

	public void setHourly(ForecastArray hourly) {
		this.hourly = hourly;
	}

	public ForecastArray getDaily() {
		return daily;
	}

	public void setDaily(ForecastArray daily) {
		this.daily = daily;
	}

	@Override
	public String toString() {
		return "ForecastResponse [offset=" + offset + ", currently="
				+ currently + ", hourly=" + hourly + ", daily=" + daily + "]";
	}

	protected ForecastResponse(Parcel in) {
		offset = in.readInt();
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
		dest.writeInt(offset);
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

	/**
	 * Applies the changes required in order to achieve a persistent data flow.
	 */
	public final void selfValidate() {
		convertToProperTime();
		fillInDataGapsHourly();
		fillInDataGapsDaily();
	}

	/**
	 * Converts from classic epoch to milliseconds and applies the GMT offset
	 * specified in the response
	 */
	public final void convertToProperTime() {
		currently.applyTimeConvertion(offset);
		for (Forecast forecast : hourly.getData()) {
			forecast.applyTimeConvertion(offset);
		}
		for (Forecast forecast : daily.getData()) {
			forecast.applyTimeConvertion(offset);
		}
	}

	/**
	 * Fills in any missing blocks of data
	 */
	private final void fillInDataGapsHourly() {
		if (hourly.getData() == null || hourly.getData().size() == 0) {
			LogUtil.e("No hourly entries in forecast response!");
			return;
		}
		if (hourly.getData().size() > ENTRIES_COUNT_HOURLY) {
			LogUtil.e("Too many entries in hourly array of forecast response: "
					+ hourly.getData().size());
			// we'll still try to fix any gaps
		}
		long time = getStartingPointHourly();
		for (int i = 0; i < hourly.getData().size(); i++) {
			if (hourly.getData().get(i).getTime() != time) {
				if (hourly.getData().get(i).getTime() % DateUtil.HOUR != 0) {
					// if this entry doesn't have an exact time then quit this
					// fixing process - the data is corrupt
					LogUtil.e("Corrupt data in hourly array of forecast response!");
					return;
				}
				hourly.getData().add(i, new Forecast(time));
			}
			time += DateUtil.HOUR;
		}
	}

	private final void fillInDataGapsDaily() {
		if (daily.getData() == null || daily.getData().size() == 0) {
			LogUtil.e("No daily entries in forecast response!");
			return;
		}
		if (daily.getData().size() > ENTRIES_COUNT_DAILY) {
			LogUtil.e("Too many entries in daily array of forecast response: "
					+ daily.getData().size());
			// we'll still try to fix any gaps
		}
		long time = currently.getTime();
		for (int i = 0; i < daily.getData().size(); i++) {
			if (daily.getData().get(i).getTime() / DateUtil.DAY != time
					/ DateUtil.DAY) {
				daily.getData().add(i, new Forecast(time));
			}
			time += DateUtil.DAY;
		}
	}

	/**
	 * 
	 * @return the expected time of the first entry in the hourly array
	 */
	private final long getStartingPointHourly() {
		// Round down to the closest hour
		return currently.getTime() - (currently.getTime() % DateUtil.HOUR);
	}

}