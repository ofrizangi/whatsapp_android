package com.example.whatsappandriodclient.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.ContactAPI;
//import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactToAdd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserRepository {

    private ContactDao contactDao;
    private ContactListData contactListData;
    private ContactAPI api;


    public UserRepository(){

//        LocalDB db = Room.inMemoryDatabaseBuilder(
//                        InstrumentationRegistry.getContext(),
//                        LocalDB.class)
//                .build();
//        LocalDB db = Room.databaseBuilder(getApplicationContext(), LocalDB.class,  "contact").
//                allowMainThreadQueries().build();

//        LocalDB  db = Room.databaseBuilder(ChatListActivity.getInstance(), LocalDB.class, "five")
//                .allowMainThreadQueries()
//                .build();
//        contactDao = db.contactDao();

        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.contactDao = db.contactDao();
        this.contactListData = new ContactListData();
        this.api = ContactAPI.getInstance();
    }



    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData(){
            super();
            List<Contact> contacts = contactDao.index();
            // every time we will do set it will call all the observers
            setValue(contacts);
        }

//        @Override
//        protected void onActive(){
//            super.onActive();
//
//            new Thread(() -> {
//                contactListData.postValue(contactDao.get());
//            }).start();
//        }

    }

    public LiveData<List<Contact>> getAll(){
        return this.contactListData;
    }

//    public void addContact(final ContactToAdd contact, final String token, String userID){
//        this.api.addContact(contact, token);
//        Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID);
//        this.contactDao.insert(contact1);
//
//    }

}
