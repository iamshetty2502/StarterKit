package com.app.utils;

import android.util.Log;

import com.app.BuildConfig;

public class LogUtils {

    public static Boolean isDev = BuildConfig.DEBUG;
    private static String TAG = "LogUtils";

    static public void LogError(String message) {
        if (isDev) {
            Log.e(TAG, message);
        }
    }

    static public void LogDebug(String message) {
        Log.d(TAG, message);
    }

    static public void LogVerbose(String message) {
        Log.v(TAG, message);
    }

    static public void LogError(String TAG, String message) {
        Log.e(TAG, message);
    }

    static public void LogDebug(String TAG, String message) {
        Log.e(TAG, message);
    }

    static public void LogVerbose(String TAG, String message) {
        Log.e(TAG, message);
    }
}
