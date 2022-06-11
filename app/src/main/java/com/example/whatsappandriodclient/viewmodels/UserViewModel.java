package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactToAdd;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.List;


//The view model wont changed the data - this is the job of the repository
public class UserViewModel extends ViewModel {

    private UserRepository cRepository;

    private LiveData<List<Contact>> contactList;


    public UserViewModel(){
        this.cRepository = new UserRepository();
        this.contactList = cRepository.getAll();
    }

    public LiveData<List<Contact>> get(){
        return contactList;
    }

    public Contact getAContact(int position){
        return contactList.getValue().get(position);
    }

}
