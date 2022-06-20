package com.example.whatsappandriodclient.repositories;

import com.example.whatsappandriodclient.ChatActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.ContactAPI;
import com.example.whatsappandriodclient.api.MessageAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageRepository {

    private MessageDao messageDao;
    private ContactDao contactDao;
    private MessageAPI api;
    private ContactAPI contactAPI;

    public MessageRepository(){

        LocalDB db = LocalDB.getDatabase(ChatActivity.getInstance());
        this.messageDao = db.messageDao();
        this.contactDao = db.contactDao();
        this.api = MessageAPI.getInstance();
        this.contactAPI = ContactAPI.getInstance();
    }



    public void addMessageToDao(final Message message, final String contactId, String token, String contactName){
        this.messageDao.insert(message);
        Contact c = this.contactDao.get(contactId);
        c.setLastMessage(message.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = new Date();
        String s =  sdf.format(date);
        c.setLastDate(s);
        this.contactDao.update(c);
    }


    public void addMessage(final SendMessage message, final String token, final String contactName, final String contactId, final String userName){
        this.api.sendMessage(message, token, contactName, contactDao, contactId, userName);
        Message message1 = new Message(message.getContent(), new Date(), true, contactId);
        addMessageToDao( message1, contactId, token, contactName);
    }










}
