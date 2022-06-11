package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private String userName;

    public UserViewModelFactory(String userName){
        this.userName = userName;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new UserViewModel(userName);
    }


}
