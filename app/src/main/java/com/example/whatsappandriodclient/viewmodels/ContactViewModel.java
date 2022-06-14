package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.Invitation;
import com.example.whatsappandriodclient.repositories.ContactRepository;

public class ContactViewModel extends ViewModel {

    private ContactRepository cRepository;
//    private LiveData<List<Contact>> contactList;

    public ContactViewModel(int contactId){
        this.cRepository = new ContactRepository(contactId);
//        this.contactList = cRepository.getAll();
    }

    public ContactViewModel(){
        this.cRepository = new ContactRepository();
    }



    public void addContact(String token , ContactToAdd contact, String userID, Invitation invitation){
        this.cRepository.addContact(contact,  token, userID, invitation);
    }


    public void updateMessages(String token, String contactName){
        this.cRepository.updateMessages(token,  contactName);
    }

//    public void inviteContact(Invitation invitation, String inviteServer, String token , String contactName){
//        this.cRepository.inviteContact(invitation, inviteServer, token, contactName);
//    }


}
