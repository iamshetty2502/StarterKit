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

    public static String ordinalSuffixOf(int i) {
        int j = i % 10;
        int k = i % 100;
        if (j == 1 && k != 11) {
            return i + "st";
        }
        if (j == 2 && k != 12) {
            return i + "nd";
        }
        if (j == 3 && k != 13) {
            return i + "rd";
        }
        return i + "th";
    }
}
