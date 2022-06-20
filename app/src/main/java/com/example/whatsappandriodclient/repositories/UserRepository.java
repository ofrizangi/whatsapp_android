package com.example.whatsappandriodclient.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.ContactAPI;
import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactsOfUser;
import com.example.whatsappandriodclient.objectAPI.ContactGet;
import com.example.whatsappandriodclient.objectAPI.TokenApplication;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private ContactDao contactDao;
//    private ContactDao contactDao;
    private ContactListData contactListData;
    private UserAPI api;
    private ContactAPI apiContact;
    private List<Contact> contacts;
    private String userName;

    public UserRepository(String userName){
        this.userName = userName;


        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.userDao = db.userDao();
        this.contactDao = db.contactDao();
        this.contactListData = new ContactListData();
        this.api = UserAPI.getInstance();
        this.apiContact = ContactAPI.getInstance();
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

    public void setContactView(){
        this.contactListData.contacts = join();
        contactListData.postValue(this.contactListData.contacts);
    }



    class ContactListData extends MutableLiveData<List<Contact>> {

        private List<Contact> contacts;

        public ContactListData(){
            super();
            contacts = join();
            setValue(contacts);
            // every time we will do set it will call all the observers
        }


    }



    public List<Contact> insertContactsToDao(List<ContactGet> contactGets){
        List<Contact> contacts = new ArrayList<>();
        for(ContactGet contact: contactGets){
            contacts.add(new Contact(this.userName + contact.getId(), contact.getId(), contact.getName(), contact.getServer(),this.userName,  contact.getLast(), contact.getLastdate()));
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


public void sendTokenToServer(TokenApplication tokenApplication, String tokenUser){
        api.sendToken(tokenApplication,tokenUser);
    }
}
