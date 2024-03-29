package com.example.whatsappandriodclient.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MessagesOfContact {

    @Embedded
    public Contact contact;
    @Relation(
            parentColumn = "key",
            entityColumn = "contactKey"
    )
    public List<Message> messages;

    public MessagesOfContact(Contact contact, List<Message> messages) {
        this.contact = contact;
        this.messages = messages;
    }
}
