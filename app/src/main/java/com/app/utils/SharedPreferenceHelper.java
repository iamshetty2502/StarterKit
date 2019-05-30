

package com.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.App;

/// A wrapper/abstraction layer over SharedPreferences which features storage/retrieving data from/to SharedPreferences
public class SharedPreferenceHelper {

    private static final String TAG = SharedPreferenceHelper.class.getSimpleName();
    /// Shared preferences name
    private static String APP_PREFS = "AppPrefs";

    /// Get instance of shared preferences in private mode
    private static SharedPreferences getSharedPrefs() {
        return App.getAppContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    /// Get instance of Editor
    private static SharedPreferences.Editor getEditor() {
        return getSharedPrefs().edit();
    }

    /// Get locally stored value for specific key in the desired data type
    /// The value can be null if it does not exist or cannot be casted to the desired data type
    public static <T extends Object> T getValue(String key, Object defaultValue, Class<T> type) {
        Object value = null;
        SharedPreferences sharedPrefs = getSharedPrefs();
        if (type.equals(String.class)) {    // String
            String defVal = "";
            if (defaultValue != null && defaultValue instanceof String) {
                defVal = (String) defaultValue;
            }
            try {
                value = sharedPrefs.getString(key, defVal);
            } catch (Exception e) {
                LogUtils.LogError(TAG, "Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Integer.class)) {    // Integer
            int defVal = 0;
            if (defaultValue != null && defaultValue instanceof Integer) {
                defVal = (int) defaultValue;
            }
            try {
                value = sharedPrefs.getInt(key, defVal);
            } catch (Exception e) {
                LogUtils.LogError(TAG, "Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Float.class)) {  // Float
            float defVal = 0;
            if (defaultValue != null && defaultValue instanceof Float) {
                defVal = (float) defaultValue;
            }
            try {
                value = sharedPrefs.getFloat(key, defVal);
            } catch (Exception e) {
                LogUtils.LogError(TAG, "Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Boolean.class)) {    // Boolean
            boolean defVal = false;
            if (defaultValue != null && defaultValue instanceof Boolean) {
                defVal = (boolean) defaultValue;
            }
            try {
                value = sharedPrefs.getBoolean(key, defVal);
            } catch (Exception e) {
                LogUtils.LogError(TAG, "Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        } else if (type.equals(Long.class)) {   // Long
            long defVal = 0;
            if (defaultValue != null && defaultValue instanceof Long) {
                defVal = (long) defaultValue;
            }
            try {
                value = sharedPrefs.getLong(key, defVal);
            } catch (Exception e) {
                LogUtils.LogError(TAG, "Error getting value for key: " + key + "\nError: " + e.getMessage());
            }

        }
        return type.cast(value);
    }

    /// Store data locally for specific key
    public static void putValue(String key, Object value) {
        SharedPreferences.Editor editor = getEditor();
        if (value instanceof String) {    // String
            editor.putString(key, (String) value);

        } else if (value instanceof Integer) {    // Integer
            editor.putInt(key, (int) value);

        } else if (value instanceof Float) {  // Float
            editor.putFloat(key, (float) value);

        } else if (value instanceof Boolean) {    // Boolean
            editor.putBoolean(key, (boolean) value);

        } else if (value instanceof Long) {   // Long
            editor.putLong(key, (long) value);
        }
        editor.commit();
    }


    public static String getAuthToken() {
        return SharedPreferenceHelper.getValue(Constants.AUTH_TOKEN, "-1", String.class);
    }

    public static String getDisplayName() {
        return SharedPreferenceHelper.getValue(Constants.DISPLAY_NAME, "ROBOT X", String.class);
    }


    public static void clearData() {
        SharedPreferenceHelper.getSharedPrefs().edit().clear().apply();
    }
}
