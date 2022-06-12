package com.example.whatsappandriodclient.repositories;

import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.LoginActivity;
import com.example.whatsappandriodclient.api.MessageAPI;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.entities.SendMessage;

public class MessageRepository {

    private MessageDao messageDao;
    //    private UserRepository.ContactListData contactListData;
    private MessageAPI api;

    public MessageRepository(){

        LocalDB db = LocalDB.getDatabase(LoginActivity.getInstance());
        this.messageDao = db.messageDao();
        this.api = MessageAPI.getInstance();
    }


    public void addMessage(final SendMessage message, final String token, final String contactName){
        this.api.sendMessage(message, token, contactName);
//        Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID);
//        this.contactDao.insert(contact1);
    }










}
