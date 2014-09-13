package com.dpanayotov.simpleweather.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;

import com.dpanayotov.simpleweather.R;

public class LocationUtil {
	public static boolean areLocationServicesEnabled(Context context) {
		LocationManager lm = null;
		boolean gps_enabled = false, network_enabled = false;
		if (lm == null)
			lm = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = lm
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}

		return gps_enabled || network_enabled;
	}

	public static void openLocationEnableDialog(final Context context,
			DialogInterface.OnClickListener onSettingsListener,
			DialogInterface.OnClickListener onCancelListener) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setMessage(context.getResources().getString(
				R.string.gps_network_not_enabled));
		dialog.setCancelable(false);
		dialog.setPositiveButton(
				context.getResources().getString(
						R.string.open_location_settings), onSettingsListener);
		dialog.setNegativeButton(context.getString(R.string.cancel),
				onCancelListener);
		dialog.show();
	}
}
