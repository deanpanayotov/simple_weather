package com.dpanayotov.simpleweather.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

import com.dpanayotov.simpleweather.R;

public class ProcessDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		setCancelable(false);

		final Activity activity = getActivity();
		View parent = activity.getLayoutInflater().inflate(
				R.layout.fragment_process_dialog, null, false);
		Dialog dialog = new Dialog(activity, R.style.ProgressDialogTheme);
		dialog.setContentView(parent);

		return dialog;
	}

}
