package com.example.whatsappandriodclient;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.whatsappandriodclient.viewmodels.ContactViewModel;
import com.example.whatsappandriodclient.viewmodels.MessageViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagesService extends FirebaseMessagingService {

    private final String CHANNEL_ID = "1";
    private MessageViewModel messageViewModel;
    private ContactViewModel viewModelContact;
    private String token;;
    private String contactUserName;
    private String contactId;
    private String userName;
    public MessagesService(MessageViewModel messageViewModel, ContactViewModel contactViewModel,String token, String contactUserName,
                           String contactId, String userName) {
        this.messageViewModel = messageViewModel;
        this.viewModelContact =contactViewModel;
        this.token =token;
        this.contactUserName = contactUserName;
        this.contactId = contactId;
        this.userName = userName;
    }


    public MessagesService() {}

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        Log.i("here", "here");

        String contact = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        String userName = message.getData().get("userName");

        String id = userName + contact;


        if(ChatActivity.getInstance() == null || !ChatActivity.getInstance().updateContactFirebase(contact, content, id)){
            if(ChatActivity.getInstance() == null){
                ChatListActivity.getInstance().updateFirebase(content, id, contact);
            }
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(message.getNotification().getTitle())
                    .setContentText(message.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        }

    }


    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Chat";
            String description = "Chat";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}