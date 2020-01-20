package com.github.elwyncrestha.wearabledemo.channel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

/**
 * @author Elvin Shrestha on 1/20/20
 */
public class ChannelUtils {

    Context context;
    public static final String CHANNEL_1 = "Channel 1";
    public static final String CHANNEL_2 = "Channel 2";

    public ChannelUtils(Context context) {
        this.context = context;
    }

    public void create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1,
                    CHANNEL_1,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2,
                    CHANNEL_2,
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is channel 2");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
