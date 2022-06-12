package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.objectAPI.UserRegister;
import com.example.whatsappandriodclient.repositories.AppRepository;


//The view model wont changed the data - this is the job of the repository
public class AppViewModel extends ViewModel {

    private AppRepository cRepository;


    public AppViewModel(){
        this.cRepository = new AppRepository();
    }

    public void updateUsers() {
        cRepository.get();
    }

    public void addUser(UserRegister userRegister){
        cRepository.addUser(userRegister);
    }

    public void sendUser(UserLogin userLogin){
        cRepository.sendUser(userLogin);
    }


}