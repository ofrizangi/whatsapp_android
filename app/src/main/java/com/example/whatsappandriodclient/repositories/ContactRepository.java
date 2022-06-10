package com.example.whatsappandriodclient.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Contact;

import java.util.LinkedList;
import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private ContactListData contactListData;
    private UserAPI api;


    public ContactRepository(){

//        LocalDB db = Room.inMemoryDatabaseBuilder(
//                        InstrumentationRegistry.getContext(),
//                        LocalDB.class)
//                .build();
//        LocalDB db = Room.databaseBuilder(getApplicationContext(), LocalDB.class,  "contact").
//                allowMainThreadQueries().build();

//        LocalDB db = LocalDB.getInstance();
//        this.contactDao = db.contactDao();
        this.contactListData = new ContactListData();
//        this.api = UserAPI.getInstance();
    }



    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData(){
            super();
            List<Contact> contacts = new LinkedList<>();

            // if we work with room instead of all this we can do go to the db in the onActive function
            contacts.add(new Contact(1,"ofri", "ofri", "localhost" ));
            contacts.add(new Contact(2,"sivan", "sivan", "localhost" ));
            contacts.add(new Contact(3,"dcs", "ds", "localhost" ));

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

//    public void addContact(final Contact contact){
//        this.api.add(contact);
//    }




}
