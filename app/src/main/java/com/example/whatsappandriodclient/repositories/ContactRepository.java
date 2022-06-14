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
    private String contactKey;
    private MessagListData messagListData;


    public ContactRepository(String contactKey){

        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.contactDao = db.contactDao();
        this.api = ContactAPI.getInstance();
        this.messageDao = db.messageDao();
        this.contactKey = contactKey;
        this.messagListData = new MessagListData();
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
            if (messagesOfContact.contact.getKey().equals(this.contactKey)) {
                messageList = messagesOfContact.messages;
                return messageList;
            }
        }
        return messageList;
    }

    public List<Message> insertMessageToRoom(List<GetMessage> messages){
        List<Message> newMessageList = new ArrayList<>();

        for(GetMessage message: messages){
            newMessageList.add(new Message(message.getContent(), stringToDate(message.getCreated()) , message.isSent(), this.contactKey));
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

    public void addMessageToView(Message message){
        this.messagListData.messages.add(message);
        messagListData.setValue(this.messagListData.messages);
    }


    class MessagListData extends MutableLiveData<List<Message>> {

        private List<Message> messages;

        public MessagListData() {
            super();
            this.messages = join();
//            List<Message> messages = new ArrayList<>();
//            messages.add(new Message("hy", new Date(), true, 1));
//            messages.add(new Message("gy", new Date(), true, 1));
//            messages.add(new Message("ty", new Date(), true, 1));
//            messages.add(new Message("ey", new Date(), true, 1));
            setValue(messages);
            // every time we will do set it will call all the observers
        }

//        @Override
//        protected void onActive(){
//            super.onActive();
//
//            new Thread(() -> {
//                messagListData.postValue(contactDao.get(contactKey));
//            }).start();
//        }
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

    public void addContact(ContactToAdd contact, String token, String userName, Invitation invitation){
        this.api.addContact(contact, token, userName, invitation, contactDao);
    }

//    public void inviteContact(final Invitation invitation, final String inviteServer){
//        this.api.inviteContact(invitation, inviteServer);
//    }

    public void updateMessages(String token, String contactName){
        this.api.getAllMessages(token, this, contactName, this.messagListData);
    }
}
