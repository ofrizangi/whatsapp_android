package com.example.whatsappandriodclient.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactsOfUser;
import com.example.whatsappandriodclient.objectAPI.ContactGet;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private ContactDao contactDao;
//    private ContactDao contactDao;
    private ContactListData contactListData;
    private UserAPI api;
    private List<Contact> contacts;
    private String userName;

    public UserRepository(String userName){
        this.userName = userName;

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
        this.userDao = db.userDao();
        this.contactDao = db.contactDao();
//        this.contactDao = db.contactDao();
        this.contactListData = new ContactListData();
        this.api = UserAPI.getInstance();
    }


    public List<Contact> join() {
        List<Contact> contactList = new ArrayList<>();
        List<ContactsOfUser> contacts = userDao.getContactsOfUser();
        for (ContactsOfUser contactsOfUser : contacts) {
            if (contactsOfUser.user.getUserName().equals(userName)) {
                contactList = contactsOfUser.contacts;
                return contactList;
            }
        }
        return contactList;
    }



    class ContactListData extends MutableLiveData<List<Contact>> {

        public ContactListData(){
            super();
            List<Contact> contacts = join();
            setValue(contacts);
            // every time we will do set it will call all the observers
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



    public List<Contact> insertContactsToDao(List<ContactGet> contactGets){
        List<Contact> contacts = new ArrayList<>();
        for(ContactGet contact: contactGets){
            contacts.add(new Contact(contact.getId(), contact.getName(), contact.getServer(), userName));
        }
        List<Contact> contactList = join();
        contactDao.deleteMany(contactList);
        contactDao.insertMany(contacts);
        List<Contact> my = contactDao.index();

        return contacts;
    }

    public LiveData<List<Contact>> getAll(){
        return this.contactListData;
    }

    public void getAllContacts(String token){
         api.getAllContacts(token, this, this.contactListData);
    }



//    public void addContact(final ContactToAdd contact, final String token, String userID){
//        this.api.addContact(contact, token);
//        Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID);
//        this.contactDao.insert(contact1);
//
//    }

}
