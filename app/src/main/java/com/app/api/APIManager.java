package com.app.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.App;

import java.util.HashMap;
import java.util.Map;

public class APIManager {

    public static String NO_CONNECTIVITY_ERROR = "The Internet connection appears to be offline.";
    public static String GENERIC_API_ERROR_MESSAGE = "Some error occurred. Please try again later.";
    private static int statusCode = 0;
    private static Map<String, String> responseHeaders = new HashMap<>();
    //private static ProgressDialog progressDialog;

    /**
     * Make api request to server.
     *
     * @param apiRequest  - api request object.
     * @param apiResponse - api response completion with error and response received from server.
     */
    public static void request(final APIRequest apiRequest, final APIResponse apiResponse) {
        // return if url is invalid
        if (apiRequest.url == null || apiRequest.url.length() == 0) {
            // Show error and print log
            return;
        }
        // Show loader if required
        /*if (apiRequest.context != null && apiRequest.showLoader) {
            // progressDialog = new ProgressDialog(apiRequest.context);
            progressDialog = new ProgressDialog(apiRequest.context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }*/
        // API Call
        final StringRequest stringRequest = new StringRequest(apiRequest.method, apiRequest.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Check token expiry here...
                /*if (tokenExpired) {
                    // Authorization failure
                    // Perform logout
                    User.logout();
                    // Send login status changed broadcast
                    if (apiRequest.context != null) {
                        Intent loginStatusChanged = new Intent();
                        loginStatusChanged.setAction(Constant.ACTION_LOGIN_STATUS_CHANGED);
                        apiRequest.context.sendBroadcast(loginStatusChanged);
                    }
                    return;
                }*/
                if (apiResponse != null) {
                    apiResponse.onResponse(response, null, responseHeaders, statusCode);
                }
                // Hide loader if shown
                if (apiRequest.context != null && apiRequest.showLoader) {
                    /*if (progressDialog != null) {
                        progressDialog.dismiss();
                    }*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (apiRequest.context != null && apiRequest.showError && apiRequest.showLoader) {
                    String errorText = GENERIC_API_ERROR_MESSAGE;
                    if (error instanceof NoConnectionError) {
                        errorText = NO_CONNECTIVITY_ERROR;
                    } else {
                        if (APIConstants.getEnvironment() != APIConstants.Environment.PRODUCTION) {
                            if (error != null && error.getLocalizedMessage() != null) {
                                errorText = error.getLocalizedMessage();
                            }
                        }
                    }
                    //AlertUtils.showAlert("Error", errorText, null, apiRequest.context, null);
                }
                if (apiResponse != null) {
                    apiResponse.onResponse(null, error, responseHeaders, statusCode);
                }
                // Hide loader if shown
                if (apiRequest.context != null && apiRequest.showLoader) {
                    /*if (progressDialog != null) {
                        progressDialog.dismiss();
                    }*/
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (apiRequest.params == null) {
                    apiRequest.params = new HashMap<>();
                }
                return apiRequest.params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (apiRequest.headers == null || !apiRequest.allowRequestHeaders) {
                    apiRequest.headers = new HashMap<>();
                }
                return apiRequest.headers;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                statusCode = response.statusCode;
                responseHeaders = response.headers;
                return super.parseNetworkResponse(response);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };
        stringRequest.setTag("itap_api");
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(4000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        APIRequestQueue.sharedinstance().addToRequestQueue(stringRequest);
    }

    /**
     * Checks if internet is available or not.
     *
     * @return true if internet available else false.
     */
    public static boolean isConnectedToInternet() {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) App.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }

    public static void cancelAllRequests() {
        APIRequestQueue.sharedinstance().cancelAll();
    }

}
