package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ContactViewModelFactory implements ViewModelProvider.Factory {
    private String contactId;

    public ContactViewModelFactory(String contactId){
        this.contactId = contactId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ContactViewModel(contactId);
    }
}
