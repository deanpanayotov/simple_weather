package com.dpanayotov.simpleweather.api.response;

import android.os.Parcel;
import android.os.Parcelable;

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
	public long getTime() {
		return time;
	}
	public String getSummary() {
		return summary;
	}
	public String getIcon() {
		return icon;
	}
	public float getPrecipIntensity() {
		return precipIntensity;
	}
	public float getPrecipProbability() {
		return precipProbability;
	}
	public String getPrecipType() {
		return precipType;
	}
	public float getTemperatureMin() {
		return temperatureMin;
	}
	public float getTemperatureMax() {
		return temperatureMax;
	}
	public float getTemperature() {
		return temperature;
	}
	public float getHumidity() {
		return humidity;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public int getWindBearing() {
		return windBearing;
	}
	public float getCloudCover() {
		return cloudCover;
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
				+ cloudCover + "]";
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
}