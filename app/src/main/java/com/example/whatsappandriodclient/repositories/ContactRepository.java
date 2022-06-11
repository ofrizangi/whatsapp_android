package com.example.whatsappandriodclient.repositories;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.api.ContactAPI;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactToAdd;

public class ContactRepository {

    private ContactDao contactDao;
//    private UserRepository.ContactListData contactListData;
    private ContactAPI api;


    public ContactRepository(){

        LocalDB db = LocalDB.getDatabase(ChatListActivity.getInstance());
        this.contactDao = db.contactDao();
        this.api = ContactAPI.getInstance();
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
        Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID);
        this.contactDao.insert(contact1);

    }
}
