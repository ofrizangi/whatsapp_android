package com.example.whatsappandriodclient;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagesService extends FirebaseMessagingService {
    public MessagesService() {
    }

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

//        if(r)
        Log.i("recieved", message.getNotification().getBody());


    }
}