package com.github.elwyncrestha.wearabledemo.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * @author Elvin Shrestha on 1/20/20
 */
public class CustomBroadcastReceiver extends BroadcastReceiver {

    private WifiListener wifiListener;

    public void setWifiListener(WifiListener wifiListener) {
        this.wifiListener = wifiListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            wifiListener.onChange(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false));
        }
    }

    /**
     * Callback listener to emit wifi status.
     */
    public interface WifiListener {
        void onChange(boolean status);
    }
}
