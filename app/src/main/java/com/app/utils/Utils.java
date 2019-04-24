package com.app.utils;

public class Utils {

    public static String getTextCaps(String text) {
        String[] strArray = text.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            builder.append(cap).append(" ");
        }
        return builder.toString();
    }
}
