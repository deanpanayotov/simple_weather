package com.dpanayotov.simpleweather.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.dpanayotov.simpleweather.util.DateUtil;
import com.google.gson.annotations.SerializedName;

public class Forecast implements Parcelable {
	@SerializedName("time")
	private long time;
	@SerializedName("summary")
	private String summary;
	@SerializedName("icon")
	private String icon;
	@SerializedName("precipIntensity")
	private float precipIntensity;
	@SerializedName("precipProbability")
	private float precipProbability;
	@SerializedName("precipType")
	private String precipType;
	@SerializedName("temperatureMin")
	private float temperatureMin;
	@SerializedName("temperatureMax")
	private float temperatureMax;
	@SerializedName("temperature")
	private float temperature;
	@SerializedName("humidity")
	private float humidity;
	@SerializedName("windSpeed")
	private float windSpeed;
	@SerializedName("windBearing")
	private int windBearing;
	@SerializedName("cloudCover")
	private float cloudCover;
	private boolean isBlankEntry = false;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public float getPrecipIntensity() {
		return precipIntensity;
	}

	public void setPrecipIntensity(float precipIntensity) {
		this.precipIntensity = precipIntensity;
	}

	public float getPrecipProbability() {
		return precipProbability;
	}

	public void setPrecipProbability(float precipProbability) {
		this.precipProbability = precipProbability;
	}

	public String getPrecipType() {
		return precipType;
	}

	public void setPrecipType(String precipType) {
		this.precipType = precipType;
	}

	public float getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(float temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public float getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(float temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public int getWindBearing() {
		return windBearing;
	}

	public void setWindBearing(int windBearing) {
		this.windBearing = windBearing;
	}

	public float getCloudCover() {
		return cloudCover;
	}

	public void setCloudCover(float cloudCover) {
		this.cloudCover = cloudCover;
	}

	public boolean isBlankEntry() {
		return isBlankEntry;
	}

	public void setBlankEntry(boolean isBlankEntry) {
		this.isBlankEntry = isBlankEntry;
	}

	@Override
	public String toString() {
		return "Forecast [time=" + time + ", summary=" + summary + ", icon="
				+ icon + ", precipIntensity=" + precipIntensity
				+ ", precipProbability=" + precipProbability + ", precipType="
				+ precipType + ", temperatureMin=" + temperatureMin
				+ ", temperatureMax=" + temperatureMax + ", temperature="
				+ temperature + ", humidity=" + humidity + ", windSpeed="
				+ windSpeed + ", windBearing=" + windBearing + ", cloudCover="
				+ cloudCover + ", isBlankEntry=" + isBlankEntry + "]";
	}

	protected Forecast(Parcel in) {
		time = in.readLong();
		summary = in.readString();
		icon = in.readString();
		precipIntensity = in.readFloat();
		precipProbability = in.readFloat();
		precipType = in.readString();
		temperatureMin = in.readFloat();
		temperatureMax = in.readFloat();
		temperature = in.readFloat();
		humidity = in.readFloat();
		windSpeed = in.readFloat();
		windBearing = in.readInt();
		cloudCover = in.readFloat();
		isBlankEntry = in.readByte() != 0x00;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(time);
		dest.writeString(summary);
		dest.writeString(icon);
		dest.writeFloat(precipIntensity);
		dest.writeFloat(precipProbability);
		dest.writeString(precipType);
		dest.writeFloat(temperatureMin);
		dest.writeFloat(temperatureMax);
		dest.writeFloat(temperature);
		dest.writeFloat(humidity);
		dest.writeFloat(windSpeed);
		dest.writeInt(windBearing);
		dest.writeFloat(cloudCover);
		dest.writeByte((byte) (isBlankEntry ? 0x01 : 0x00));
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() {
		@Override
		public Forecast createFromParcel(Parcel in) {
			return new Forecast(in);
		}

		@Override
		public Forecast[] newArray(int size) {
			return new Forecast[size];
		}
	};

	public final void applyTimeConvertion(int offset) {
		time = DateUtil.convertGMTTimeToLocalTimezone(time * DateUtil.SECOND,
				offset);
	}

	/**
	 * This constructor is used when filling in the gaps in the data received in
	 * the response
	 * 
	 * @param time
	 *            time point at which an entry is missing and being replaced by
	 *            this blank one
	 */
	public Forecast(long time) {
		this.time = time;
		this.isBlankEntry = true;
	}

}