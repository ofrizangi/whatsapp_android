package com.example.whatsappandriodclient;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.viewmodels.ContactViewModel;
import com.example.whatsappandriodclient.viewmodels.MessageViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

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

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String userName = message.getData().get("userName");
        if (userName != this.userName) {
        String contact = message.getNotification().getTitle();
        String content = message.getNotification().getBody();

        SendMessage sendMessage = new SendMessage(content);
        messageViewModel.addMessageToDao(sendMessage, contactId);
        //screen
        if (contact.equals(this.contactUserName)) {
//            Message message1 = new Message(sendMessage.getContent(), new Date(), false, contactId);
//            viewModelContact.addMessageToView(message1);
        }
        //notification
        else {
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
        Log.i("recieved", message.getNotification().getBody());
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