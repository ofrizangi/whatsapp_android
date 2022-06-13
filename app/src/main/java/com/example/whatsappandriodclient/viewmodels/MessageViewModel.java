package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.repositories.MessageRepository;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private MessageRepository messageRepository;

    public MessageViewModel(){
        this.messageRepository = new MessageRepository();
    }

    public void addMessage(String token , SendMessage message, String contactName, int contactId){
        this.messageRepository.addMessage(message, token, contactName, contactId);
    }



}
