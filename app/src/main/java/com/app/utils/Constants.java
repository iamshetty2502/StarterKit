package com.app.utils;

import com.app.BuildConfig;

public class Constants {
    public static final String LOGGEDOUT = "-11111";
    //For Database
    public static String DatabaseName = BuildConfig.DATABASE_NAME;
    public static int IGNORE = -19920225;
    public static String AUTH_TOKEN = "AUTH_TOKEN";
    public static String DISPLAY_NAME = "DISPLAY_NAME";

    public static void logout() {
        SharedPreferenceHelper.putValue(Constants.AUTH_TOKEN, Constants.LOGGEDOUT);
        SharedPreferenceHelper.clearData();
    }

}
