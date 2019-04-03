package com.app.api;


import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class APIRequest {

    public String url = "";
    public int method;
    public Map<String, String> params = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();
    public Context context;
    public boolean showLoader;
    public boolean showError; // Set default value as per the app environment or do it in constructor
    // timeOutInterval
    // retry policy
    public boolean allowRequestHeaders = true;

    public APIRequest() {
    }

    public APIRequest(String url, int method, Map<String, String> params, Map<String, String> headers, Context context) {
        this.url = url;
        this.method = method;
        this.params = params;
        // If authToken in headers is mandatory,
        // Create new if headers is null and pass authToken here
        //TODO change the value to constants
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("x-api-key", "FJssllekan84rX7yKTmhP5Tdi9YIwacm69G5MCTv");
        }
        this.headers = headers;
        if (context != null) {
            this.context = context;
            showLoader = true;
            showError = true;
        }
    }

}
