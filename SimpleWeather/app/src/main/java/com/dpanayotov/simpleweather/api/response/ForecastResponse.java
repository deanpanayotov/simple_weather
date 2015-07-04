package com.dpanayotov.simpleweather.api.response;

import java.util.List;
import java.util.Random;

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
		fillInDataGapsHourly();
		fillInDataGapsDaily();
	}

	/**
	 * Converts from classic epoch to milliseconds and applies the GMT offset
	 * specified in the response
	 */
	public final void convertToProperTime() {
		currently.timeToMillis();
		for (Forecast forecast : hourly.getData()) {
			forecast.timeToMillis();
		}
		for (Forecast forecast : daily.getData()) {
			forecast.timeToMillis();
			forecast.timeToGMT(offset);
		}
	}

	/**
	 * Fills in any missing blocks of data
	 */
	private final void fillInDataGapsHourly() {

		List<Forecast> hourlyData = hourly.getData();

		if (hourlyData == null || hourlyData.size() == 0) {
			LogUtil.e("No hourly entries in forecast response!");
			return;
		}
		if (hourlyData.size() > ENTRIES_COUNT_HOURLY) {
			LogUtil.e("Too many entries in hourly array of forecast response: "
					+ hourlyData.size());
			// we'll still try to fix any gaps
		}

		long time = getStartingPointHourly();
		// we first need to wipe out any corrupt data - meaning any data with a
		// date previous from the current time variable
		// loop is running backwards to avoid skipping an element when we remove
		for (int i = hourlyData.size() - 1; i >= 0; i--) {
			if (hourlyData.get(i).getTime() < time) {
				hourlyData.remove(i);
			}
		}
		for (int i = 0; i < hourlyData.size(); i++) {
			if (hourlyData.get(i).getTime() != time) {
				if (hourlyData.get(i).getTime() % DateUtil.HOUR != 0) {
					// if this entry doesn't have an exact time then quit this
					// fixing process - the data is corrupt
					LogUtil.e("Corrupt data in hourly array of forecast response!");
					return;
				}
				hourlyData.add(i, new Forecast(time));
			}
			time += DateUtil.HOUR;
		}
	}

	/**
	 * Fills in any missing blocks of data
	 */
	private final void fillInDataGapsDaily() {
		List<Forecast> dailyData = daily.getData();
		if (dailyData == null || dailyData.size() == 0) {
			LogUtil.e("No daily entries in forecast response!");
			return;
		}
		// Check the length of the list;
		if (dailyData.size() > ENTRIES_COUNT_DAILY) {
			LogUtil.e("Too many entries in daily array of forecast response: "
					+ dailyData.size());
			// we'll still try to fix any gaps
		}
		long time = currently.getTime();
		// first wipe off any dates BEFORE the current time:
		// this loop is reversed to avoid skipping an element after removal
		// loop direction doesn't matter since we're treating all elements the
		// same way
		for (int i = dailyData.size() - 1; i >= 0; i--) {
			if (dailyData.get(i).getTime() / DateUtil.DAY < time / DateUtil.DAY) {
				dailyData.remove(i);
			}
		}
		// fill in any gaps:
		// the time variable should be incremented every step - we either start
		// off with the right day or we add placeholder items until we reach a
		// correct day
		// the element that's not matching our time varialbe would be examined
		// every step until it reaches it's spot in the list
		for (int i = 0; i < dailyData.size(); i++) {
			if (dailyData.get(i).getTime() / DateUtil.DAY != time
					/ DateUtil.DAY) {
				dailyData.add(i, new Forecast(time));
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

	public void simulateMissingBlocks() {
		int hourlyToRemove = 5;
		int dailyToRemove = 2;
		List<Forecast> hourlyData = hourly.getData();
		List<Forecast> dailyData = daily.getData();

		for (int i = 0; i < hourlyToRemove; i++) {
			Random rand = new Random();
			hourlyData.remove(rand.nextInt(hourlyData.size()));
		}
		for (int i = 0; i < dailyToRemove; i++) {
			Random rand = new Random();
			dailyData.remove(rand.nextInt(dailyData.size()));
		}
	}

}