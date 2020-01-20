package com.github.elwyncrestha.wearabledemo;

import android.app.Notification;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.github.elwyncrestha.wearabledemo.channel.ChannelUtils;
import com.github.elwyncrestha.wearabledemo.listener.CustomBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private Button btnOne, btnTwo;
    private NotificationManagerCompat notificationManagerCompat;
    private CustomBroadcastReceiver broadcastReceiver = new CustomBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnOne = findViewById(R.id.btnOne);
        this.btnTwo = findViewById(R.id.btnTwo);

        this.notificationManagerCompat = NotificationManagerCompat.from(this);
        ChannelUtils channelUtils = new ChannelUtils(this);
        channelUtils.create();

        this.broadcastReceiver.setWifiListener(new CustomBroadcastReceiver.WifiListener() {
            @Override
            public void onChange(boolean status) {
                String message = status ? "Disconnected" : "Connected";
                displayNotification(
                        ChannelUtils.CHANNEL_1,
                        3,
                        R.drawable.ic_warning_black_24dp,
                        message,
                        message
                );
            }
        });

        this.btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification(
                        ChannelUtils.CHANNEL_1,
                        1,
                        R.drawable.ic_insert_drive_file_black_24dp,
                        "First message",
                        "First message body"
                );
            }
        });
        this.btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification(
                        ChannelUtils.CHANNEL_2,
                        2,
                        R.drawable.ic_warning_black_24dp,
                        "Second message",
                        "Second message body"
                );
            }
        });
    }

    private void displayNotification(String channelId, int notificationId, int icon, String title, String text) {
        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(notificationId, notification);
    }

    // BroadcastReceiver overrides
    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }
}
