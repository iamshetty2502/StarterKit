package com.app.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.App;


/**
 * Created by Deep on 8/24/2017.
 */

class APIRequestQueue {

    private static APIRequestQueue mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private APIRequestQueue() {
        // getApplicationContext() is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        mContext = App.getAppContext();
        mRequestQueue = getRequestQueue();
    }

    public static synchronized APIRequestQueue sharedinstance() {
        if (mInstance == null) {
            mInstance = new APIRequestQueue();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void cancelAll() {
        getRequestQueue().cancelAll("itap_api");
    }

}
