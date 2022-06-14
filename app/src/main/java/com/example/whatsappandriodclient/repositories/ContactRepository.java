package com.example.whatsappandriodclient.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.ContactAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.entities.MessagesOfContact;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.GetMessage;
import com.example.whatsappandriodclient.objectAPI.Invitation;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ContactRepository {

    private ContactDao contactDao;
    private MessageDao messageDao;
//    private UserRepository.ContactListData contactListData;
    private ContactAPI api;
    private int contactId;
    private MessagListData messagListData;


    public ContactRepository(int contactId){

        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.contactDao = db.contactDao();
        this.api = ContactAPI.getInstance();
        this.messageDao = db.messageDao();
        this.messagListData = new MessagListData();
        this.contactId = contactId;
    }

    public ContactRepository(){
        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.contactDao = db.contactDao();
        this.messageDao = db.messageDao();
        this.api = ContactAPI.getInstance();
        this.messagListData = new MessagListData();
    }

    public List<Message> join() {
        List<Message> messageList = new ArrayList<>();
        List<MessagesOfContact> messages = contactDao.getMessagesOfContact();
        for (MessagesOfContact messagesOfContact : messages) {
            if (messagesOfContact.contact.getId() == this.contactId) {
                messageList = messagesOfContact.messages;
                return messageList;
            }
        }
        return messageList;
    }

    public List<Message> insertMessageToRoom(List<GetMessage> messages){
        List<Message> newMessageList = new ArrayList<>();

        for(GetMessage message: messages){
            newMessageList.add(new Message(message.getContent(), stringToDate(message.getCreated()) , message.isSent(), this.contactId));
        }
        List<Message> myMessageList = join();
        messageDao.deleteMany(myMessageList);
        messageDao.insertMany(newMessageList);
        return newMessageList;
    }

    public static Date stringToDate(String aDate) {

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        ParsePosition pos = new ParsePosition(0);
        Date stringDate = format.parse(aDate, pos);
        return stringDate;

    }

    class MessagListData extends MutableLiveData<List<Message>> {

        public MessagListData() {
            super();
            List<Message> messages = join();
//            List<Message> messages = new ArrayList<>();
//            messages.add(new Message("hy", new Date(), true, 1));
//            messages.add(new Message("gy", new Date(), true, 1));
//            messages.add(new Message("ty", new Date(), true, 1));
//            messages.add(new Message("ey", new Date(), true, 1));
            setValue(messages);
            // every time we will do set it will call all the observers
        }
    }

    public LiveData<List<Message>> getAll(){
        return this.messagListData;
    }


//    class ContactListData extends MutableLiveData<List<Contact>> {
//        public ContactListData(){
//            super();
//            List<Contact> contacts = contactDao.index();
//            // every time we will do set it will call all the observers
//            setValue(contacts);
//        }
//    }

//    public LiveData<List<Contact>> getAll(){
//        return this.contactListData;
//    }

    public void addContact(final ContactToAdd contact, final String token, String userID){
        this.api.addContact(contact, token, userID);
        Contact c = contactDao.getIDMax();
        int id = c.getId() +1;
        Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID, id);
        this.contactDao.insert(contact1);
    }

    public void inviteContact(final Invitation invitation, final String inviteServer){
        this.api.inviteContact(invitation, inviteServer);
    }

    public void updateMessages(String token, String contactName){
        this.api.getAllMessages(token, this, contactName);
    }
}
