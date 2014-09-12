package com.dpanayotov.simpleweather.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.dpanayotov.simpleweather.R;
import com.dpanayotov.simpleweather.general.SimpleWeatherApplication;

//TODO these should be refactored according to the different unit systems
public class BitmapCirclesUtil {
	private static final int BITMAP_SIZE = 200;
	private static final int CIRCLE_RADIUS = BITMAP_SIZE / 2;

	private static final float TEMP_MAX_F = 113.0f;
	private static final float TEMP_MIN_F = -40.0f;
	private static final float TOTAL_TEMP_F = Math.abs(TEMP_MIN_F) + TEMP_MAX_F;

	private static final float TEMP_MAX_C = 45.0f;
	private static final float TEMP_MIN_C = -40.0f;
	private static final float TOTAL_TEMP_C = Math.abs(TEMP_MIN_C) + TEMP_MAX_C;

	private static final int TEMP_MAX_HUE_IN_DEGREES = 255;
	private static final float TEMP_MIN_RADIUS_RATIO = 0.2f;
	private static final float TEMP_LEFT_OVER_RADIUS_RATIO = 1f - TEMP_MIN_RADIUS_RATIO;

	private static final float WIND_MAX_VALUE_SI = 27.7f; // m/s
	private static final float WIND_MAX_VALUE_US = 62.0f; // mi/h
	private static final float WIND_MAX_VALUE_CA = 100.0f; // km/h

	private static final float PRECIP_MAX_INTENSITY_IN = 0.4f;
	private static final float PRECIP_MAX_INTENSITY_MM = 10.16f;
	private static final float PRECIP_MIN_ALPHA_RATIO = 0.1f;
	private static final float PRECIP_LEFT_OVER_ALPHA_RATIO = 1f - TEMP_MIN_RADIUS_RATIO;

	public static Bitmap getPrecipCircle(float precipIntensity,
			float precipProbability) {
		Paint paint = new Paint();
		paint.setColor(SimpleWeatherApplication.getContext().getResources()
				.getColor(R.color.terra_abisso));
		paint.setAlpha((int) (255 * (PRECIP_MIN_ALPHA_RATIO + PRECIP_LEFT_OVER_ALPHA_RATIO
				* (precipIntensity / getActivePrecipMaxIntensity()))));
		return getDataCircle(precipProbability, paint);
	}

	public static Bitmap getTemperatureCircle(float temperature) {
		Paint paint = new Paint();
		paint.setColor(Color.HSVToColor(new float[] {
				TEMP_MAX_HUE_IN_DEGREES * getTemperatureAsARatio(temperature),
				1.0f, 1.0f }));
		return getDataCircle(1, paint);
	}

	private static float getTemperatureAsARatio(float temperature) {
		float total = getActiveTotalTemperature(), min = getActiveMinTemperature();
		return 1 - ((temperature + Math.abs(min)) / total);
	}

	public static Bitmap getWindCircle(float wind) {
		return getDataCircle(wind / getActiveWindSpeedMaxValue());
	}

	public static Bitmap getCloudCircle(float cloudCoverage) {
		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#000000"));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
		return getDataCircle(cloudCoverage, paint);
	}

	public static Bitmap getDataCircle(float size, Paint paint) {
		Bitmap bmp = Bitmap.createBitmap(BITMAP_SIZE, BITMAP_SIZE,
				Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bmp);
		c.drawCircle(CIRCLE_RADIUS, CIRCLE_RADIUS, (CIRCLE_RADIUS) * size,
				paint);
		return bmp;
	}

	public static Bitmap getDataCircle(float size) {
		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#000000"));
		return getDataCircle(size, paint);
	}

	private static float getActiveWindSpeedMaxValue() {
		switch (SimpleWeatherApplication.getUnits()) {
		case SI:
			return WIND_MAX_VALUE_SI;
		case US:
			return WIND_MAX_VALUE_US;
		case CA:
			return WIND_MAX_VALUE_CA;
		case UK:
			return WIND_MAX_VALUE_US;
		default:
			return WIND_MAX_VALUE_SI;
		}
	}

	private static float getActivePrecipMaxIntensity() {
		if (SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US) {
			return PRECIP_MAX_INTENSITY_IN;
		} else {
			return PRECIP_MAX_INTENSITY_MM;
		}
	}

	private static float getActiveMinTemperature() {
		if (SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US) {
			return TEMP_MIN_F;
		} else {
			return TEMP_MIN_C;
		}
	}

	private static float getActiveMaxTemperature() {
		if (SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US) {
			return TEMP_MAX_F;
		} else {
			return TEMP_MAX_C;
		}
	}

	private static float getActiveTotalTemperature() {
		if (SimpleWeatherApplication.getUnits() == SimpleWeatherApplication.UNITS.US) {
			return TOTAL_TEMP_F;
		} else {
			return TOTAL_TEMP_C;
		}
	}

}
