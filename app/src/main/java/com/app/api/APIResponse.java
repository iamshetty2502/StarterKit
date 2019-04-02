package com.app.api;

import java.util.Map;

/**
 * Created by Deep on 8/24/2017.
 */

public interface APIResponse {
    void onResponse(String response, Exception error, Map<String, String> headers, int statusCode);
}
