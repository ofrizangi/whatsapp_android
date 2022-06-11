package com.example.whatsappandriodclient.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ContactsOfUser {

        @Embedded
        public User user;
        @Relation(
                parentColumn = "userName",
                entityColumn = "userID"
        )
        public List<Contact> contacts;

    public ContactsOfUser(){}

    public ContactsOfUser(User user, List<Contact> contact) {
            this.user = user;
            this.contacts = contact;
        }
    }

