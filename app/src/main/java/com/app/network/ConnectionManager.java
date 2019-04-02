package com.app.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class ConnectionManager {

    @SuppressLint("StaticFieldLeak")
    private static ConnectionManager instance = null;
    private Context context;

    private ConnectionManager() {
    }

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) createInstance();
        return instance;
    }

    public void initialise(@NonNull Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }

    public boolean isConnectedToWifi(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiCheck;
        boolean isConnected = false;
        if (connectionManager != null) {
            wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            isConnected = wifiCheck.isConnected();
        }
        return isConnected;
    }

}
