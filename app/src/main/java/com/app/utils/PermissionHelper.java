package com.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionHelper {

    public static Boolean isPermissionGranted(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void getPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    //make sure the calling activity has onRequestPermissionsResult implemented
    public static Boolean checkAndAskPermissions(Activity activity, String permission, int requestCode) {
        if (isPermissionGranted(activity, permission)) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
        return false;
    }
}
