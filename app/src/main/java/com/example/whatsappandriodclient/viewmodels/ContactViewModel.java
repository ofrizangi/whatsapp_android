package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactToAdd;
import com.example.whatsappandriodclient.repositories.ContactRepository;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.List;

public class ContactViewModel extends ViewModel {

    private ContactRepository cRepository;
//    private LiveData<List<Contact>> contactList;

    public ContactViewModel(){
        this.cRepository = new ContactRepository();
//        this.contactList = cRepository.getAll();
    }

//    public LiveData<List<Contact>> get(){
//        return contactList;
//    }


//    public Contact getAContact(int position){
//        return contactList.getValue().get(position);
//    }


    public void addContact(String token , ContactToAdd contact, String userID){
        this.cRepository.addContact(contact,  token, userID);
    }
}