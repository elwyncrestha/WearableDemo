package com.github.elwyncrestha.wearabledemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

/**
 * @author Elvin Shrestha on 1/20/20
 */
public class RandomNumberService extends Service {

    public Context context = this;
    public Handler handler = null;
    public Runnable runnable = null;

    public RandomNumberService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "RandomNumberService started!!!", Toast.LENGTH_SHORT).show();

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                double number = getRandom();
                Toast.makeText(context, "Random number: " + number, Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        Toast.makeText(context, "Service stopped!!!", Toast.LENGTH_SHORT).show();
    }

    public static double getRandom() {
        Random random = new Random();
        return random.nextDouble();
    }
}
