package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ContactViewModelFactory implements ViewModelProvider.Factory {
    private int contactId;

    public ContactViewModelFactory(int contactId){
        this.contactId = contactId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ContactViewModel(contactId);
    }
}
