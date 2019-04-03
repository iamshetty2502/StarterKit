package com.app.api;

import java.util.Map;

public interface APIResponse {
    void onResponse(String response, Exception error, Map<String, String> headers, int statusCode);
}
