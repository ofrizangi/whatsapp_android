package com.example.whatsappandriodclient;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.viewmodels.ContactViewModel;
import com.example.whatsappandriodclient.viewmodels.MessageViewModel;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

public class MessagesService extends FirebaseMessagingService {

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
        String content = message.getNotification().getBody();
        if(!content.equals("")){
            SendMessage sendMessage = new SendMessage(content);
            messageViewModel.addMessage(token, sendMessage, contactUserName, contactId, userName);
            Message message1 = new Message(sendMessage.getContent(), new Date(), false, contactId);
            viewModelContact.addMessageToView(message1);
        }
        Log.i("recieved", message.getNotification().getBody());
    }
}