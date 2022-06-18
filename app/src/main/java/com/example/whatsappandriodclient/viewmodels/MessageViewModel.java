package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.repositories.MessageRepository;

public class MessageViewModel extends ViewModel {

    private MessageRepository messageRepository;

    public MessageViewModel(){
        this.messageRepository = new MessageRepository();
    }

    public void addMessage(String token , SendMessage message, String contactName, String contactId, String userName){
        this.messageRepository.addMessage(message, token, contactName, contactId, userName);
    }
    public void addMessageToDao(Message message , String contactId){
        this.messageRepository.addMessageToDao(message, contactId);}

}
