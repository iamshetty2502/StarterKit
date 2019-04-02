package com.app.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by kalpesh on 07/11/17.
 */

public class AlertUtils {

    /**
     * Present alert with one button.
     *
     * @param title              = title for the alert dialog.
     * @param message            = message body for the alert dialog.
     * @param positiveActionText = text for positive action button. If null, takes the default value
     *                           'OK'.
     * @param context            = context to create alert dialog.
     * @param handler            = handler to get onclick event of action button.
     */
    public static void showAlert(@Nullable String title, @Nullable String message, @Nullable String positiveActionText, @NonNull Context context, @Nullable final AlertClickHandler handler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (null != title) {
            builder.setTitle(title);
        }
        if (null != message) {
            builder.setMessage(message);
        }
        String posText = context.getResources().getString(android.R.string.ok);
        if (positiveActionText != null) {
            posText = positiveActionText;
        }
        builder.setPositiveButton(posText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (null != handler) {
                    handler.alertButtonAction(true);
                }
            }
        });
        builder.setCancelable(false);
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    /**
     * Present alert with one button.
     *
     * @param title              = title for the alert dialog.
     * @param message            = message body for the alert dialog.
     * @param positiveActionText = text for positive action button. If null, takes the default value
     *                           'OK'.
     * @param negativeActionText = text for negative action button. If null, takes the default value
     *                           'Cancel'.
     * @param context            = context to create alert dialog.
     * @param handler            = handler to get onclick event of action button.
     */
    public static void showAlert(@Nullable String title, @Nullable String message, @Nullable String positiveActionText, @Nullable String negativeActionText, @NonNull Context context, @Nullable final AlertClickHandler handler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (null != title) {
            builder.setTitle(title);
        }
        if (null != message) {
            builder.setMessage(message);
        }
        String posText = context.getResources().getString(android.R.string.ok);
        if (positiveActionText != null) {
            posText = positiveActionText;
        }
        String negText = context.getResources().getString(android.R.string.cancel);
        if (negativeActionText != null) {
            negText = negativeActionText;
        }
        builder.setPositiveButton(posText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (null != handler) {
                    handler.alertButtonAction(true);
                }
            }
        });
        builder.setNegativeButton(negText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (null != handler) {
                    handler.alertButtonAction(false);
                }
            }
        });
        builder.setCancelable(false);
        //builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.show();
    }

    /**
     * Show toast message.
     *
     * @param message  = message to show in the toast.
     * @param duration = time for the toast to flash.
     * @param context  = context to create the toast.
     */
    public static void showToast(@NonNull String message, int duration, @NonNull Context context) {
        Toast.makeText(context, message, duration).show();
    }


    /**
     * Alert button action listener.
     */
    public interface AlertClickHandler {
        /**
         * Called when the action buttons on the alert dialog are clicked.
         *
         * @param isPositiveAction = if true, positive action button is clicked else negative action
         *                         button is clicked.
         */
        void alertButtonAction(boolean isPositiveAction);
    }


}
