package com.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.app.network.ConnectionManager;
import com.app.network.NetworkReceiver;
import com.app.utils.LogUtils;


public class App extends Application implements LifecycleObserver {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        LogUtils.LogError("App Started");
        // Init Networking
        ConnectionManager.getInstance().initialise(getApplicationContext());

        registerConnectivityReceiver();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void registerConnectivityReceiver() {
        try {
            // if (android.os.Build.VERSION.SDK_INT >= 26) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            //filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            //filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            // filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            registerReceiver(getConnectivityReceiver(), filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private NetworkReceiver connectivityReceiver;

    private NetworkReceiver getConnectivityReceiver() {
        if (connectivityReceiver == null) connectivityReceiver = new NetworkReceiver();

        return connectivityReceiver;
    }

}
