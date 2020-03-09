package com.example.health965.NotifivationPush;


import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("NNNNNN", "Message Notification Body: " + remoteMessage.getNotification());
    }

    @Override
    public void onNewToken(String token) {
        Log.d("", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
    }

}
