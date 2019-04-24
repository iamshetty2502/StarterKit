package com.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.app.R;
import com.app.api.APIManager;

import java.io.File;

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

    //select image from gallery or camera
    public static void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    StrictMode.VmPolicy.Builder builderStrictMode = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builderStrictMode.build());
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    if (context instanceof Activity) {
                        ((Activity) context).startActivityForResult(intent, 1);
                    } else {
                        LogUtils.LogError("mContext should be an instanceof Activity.");
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if (context instanceof Activity) {
                        ((Activity) context).startActivityForResult(intent, 2);
                    } else {
                        LogUtils.LogError("mContext should be an instanceof Activity.");
                    }
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public static void showErrorAlert(String msg, Context context) {
        if (msg == null || msg.isEmpty()) {
            msg = APIManager.GENERIC_API_ERROR_MESSAGE;
        }
        AlertUtils.showAlert(context.getString(R.string.alert_title_error),
                msg,
                context.getString(R.string.btn_ok),
                context, null);
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
