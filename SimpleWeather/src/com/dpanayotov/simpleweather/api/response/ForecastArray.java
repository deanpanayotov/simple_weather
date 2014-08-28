package com.dpanayotov.simpleweather.api.response;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ForecastArray implements Parcelable {
	@SerializedName("summary")
	private String summary;
	@SerializedName("icon")
	private String icon;
	@SerializedName("data")
	private List<Forecast> data;

	public String getSummary() {
		return summary;
	}

	public String getIcon() {
		return icon;
	}

	public List<Forecast> getData() {
		return data;
	}

	@Override
	public String toString() {
		return "ForecastArray [summary=" + summary + ", icon=" + icon
				+ ", data=" + data + "]";
	}

	protected ForecastArray(Parcel in) {
		summary = in.readString();
		icon = in.readString();
		if (in.readByte() == 0x01) {
			data = new ArrayList<Forecast>();
			in.readList(data, Forecast.class.getClassLoader());
		} else {
			data = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(summary);
		dest.writeString(icon);
		if (data == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(data);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<ForecastArray> CREATOR = new Parcelable.Creator<ForecastArray>() {
		@Override
		public ForecastArray createFromParcel(Parcel in) {
			return new ForecastArray(in);
		}

		@Override
		public ForecastArray[] newArray(int size) {
			return new ForecastArray[size];
		}
	};
}