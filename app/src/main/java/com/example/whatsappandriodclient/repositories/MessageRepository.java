package com.example.whatsappandriodclient.repositories;

import android.util.Log;

import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.LoginActivity;
import com.example.whatsappandriodclient.api.MessageAPI;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;

import java.util.Date;
import java.util.List;

public class MessageRepository {

    private MessageDao messageDao;
    //    private UserRepository.ContactListData contactListData;
    private MessageAPI api;

    public MessageRepository(){

        LocalDB db = LocalDB.getDatabase(LoginActivity.getInstance());
        this.messageDao = db.messageDao();
        this.api = MessageAPI.getInstance();
    }


    public void addMessage(final SendMessage message, final String token, final String contactName, final int contactId){
        this.api.sendMessage(message, token, contactName);
        Message message1 = new Message(message.getContent(), new Date(), true, contactId);
        this.messageDao.insert(message1);
        List<Message> messageList = this.messageDao.index();
        Log.i("h", "h");

    }










}
