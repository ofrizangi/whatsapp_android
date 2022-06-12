package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.List;


//The view model wont changed the data - this is the job of the repository
public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    private LiveData<List<Contact>> contactList;

    private String userName;


    public UserViewModel(String userName){
        this.userName = userName;
        this.userRepository = new UserRepository(userName);
        this.contactList = userRepository.getAll();
    }

    public LiveData<List<Contact>> get(){
        return contactList;
    }

    public Contact getAContact(int position){
        return contactList.getValue().get(position);
    }

    public void getAllContacts(String token){
        this.userRepository.getAllContacts(token);
    }

//    public int getSpecificContactId(int position)


}
