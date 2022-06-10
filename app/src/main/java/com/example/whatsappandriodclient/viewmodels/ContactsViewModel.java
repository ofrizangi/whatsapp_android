package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.repositories.ContactRepository;

import java.util.List;


//The view model wont changed the data - this is the job of the repository
public class ContactsViewModel extends ViewModel {

    private ContactRepository cRepository;

    private LiveData<List<Contact>> contactList;


    public ContactsViewModel(){
        this.cRepository = new ContactRepository();
        this.contactList = cRepository.getAll();
    }



    public LiveData<List<Contact>> get(){
        return contactList;
    }


}
