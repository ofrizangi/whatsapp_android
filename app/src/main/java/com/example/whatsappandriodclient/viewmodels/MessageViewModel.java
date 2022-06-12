package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.SendMessage;
import com.example.whatsappandriodclient.repositories.MessageRepository;

public class MessageViewModel extends ViewModel {
    private MessageRepository cRepository;

    public MessageViewModel(){
        this.cRepository = new MessageRepository();
    }


    public void addMessage(String token , SendMessage message, String contactName){
        this.cRepository.addMessage(message, token, contactName);
    }


}
