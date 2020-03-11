package com.example.health965.NotifivationPush;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.health965.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager manager;

    private final static String CHANNEL_ID = "com.example.health965";
    private final static String CHANNEL_NAME = "Health965";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                OreoNotificationCeate(remoteMessage);
            } else {
                NormalNotification(remoteMessage);
            }
        }
    }
    @Override
    public void onNewToken(String token) {
        Log.d("", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void OreoNotificationCeate(RemoteMessage remoteMessage) {
        int i = 0;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_NAME)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.drawable.notifi)
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID);
        getManager().notify(++i,builder.build());
    }

    public void NormalNotification(RemoteMessage remoteMessage) {
        int RquestCode = 0;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notifi)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        getManager().notify(++RquestCode,builder.build());
    }

    public NotificationManager getManager() {
        if (manager == null)
        {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return manager;
    }

}
