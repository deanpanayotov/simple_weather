package com.dpanayotov.simpleweather.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.dpanayotov.simpleweather.R;

public class DialogUtil {

	private DialogUtil() {
		// forbid instantiation
	}

	/**
	 * Listener for neutral button click
	 * 
	 */
	public static interface IDialogListener {
		void onButtonClick(DialogInterface dialog);
	}

	/**
	 * Shows native alert dialog with title, message and neutral button. <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param title
	 *            - Doalog's title as a {@link CharSequence}
	 * @param message
	 *            - Dialog's message as a {@link CharSequence}
	 * @param neutralButtonText
	 *            - Dialog's neutral button text as a {@link CharSequence}
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context,
			CharSequence title, CharSequence message,
			CharSequence neutralButtonText, final IDialogListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(false);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setNeutralButton(neutralButtonText,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						if (listener != null) {
							listener.onButtonClick(dialog);
						}
					}
				});

		builder.create().show();
	}

	/**
	 * Shows native alert dialog with title, message and neutral button with
	 * text "OK". <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param title
	 *            - Doalog's title as a {@link CharSequence}
	 * @param message
	 *            - Dialog's message as a {@link CharSequence}
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context,
			CharSequence title, CharSequence message,
			final IDialogListener listener) {
		showNeutralAlertDialog(context, title, message,
				context.getString(R.string.alert_neutral_button_text), listener);
	}

	/**
	 * Shows native alert dialog with title "Error!", message and neutral button
	 * with text "OK". <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param message
	 *            - Dialog's message as a {@link CharSequence}
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context,
			CharSequence message, final IDialogListener listener) {
		showNeutralAlertDialog(context,
				context.getString(R.string.alert_title), message,
				context.getString(R.string.alert_neutral_button_text), listener);
	}

	/**
	 * Shows native alert dialog with title, message and neutral button. <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param titleId
	 *            - Doalog's title string id
	 * @param messageId
	 *            - Dialog's message string id
	 * @param neutralButtonTextId
	 *            - Dialog's neutral button text string id
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context, int titleId,
			int messageId, int neutralButtonTextId,
			final IDialogListener listener) {
		showNeutralAlertDialog(context, context.getText(titleId),
				context.getText(messageId),
				context.getText(neutralButtonTextId), listener);
	}

	/**
	 * Shows native alert dialog with title, message and neutral button with
	 * text "OK". <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param titleId
	 *            - Doalog's title string id
	 * @param messageId
	 *            - Dialog's message string id
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context, int titleId,
			int messageId, final IDialogListener listener) {
		showNeutralAlertDialog(context, titleId, messageId,
				R.string.alert_neutral_button_text, listener);
	}

	/**
	 * Shows native alert dialog with title "Error!", message and neutral button
	 * with text "OK". <br>
	 * <br>
	 * The dialog is auto-dismissed on neutral button click, so you do not need
	 * to dismiss it!
	 * 
	 * @param context
	 *            - the {@link Context}
	 * @param messageId
	 *            - Dialog's message string id
	 * @param listener
	 *            - {@link IDialogListener} for handling neutral button click or
	 *            <code>null</code> if you do not want to handle this action.
	 */
	public static void showNeutralAlertDialog(Context context, int messageId,
			final IDialogListener listener) {
		showNeutralAlertDialog(context, R.string.alert_title, messageId,
				R.string.alert_neutral_button_text, listener);
	}

}
