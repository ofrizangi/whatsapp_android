package com.example.whatsappandriodclient.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.MessagesOfContact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> index();

    @Query("SELECT * FROM contact WHERE id= :id")
    Contact get(int id);

    @Transaction
    @Query("SELECT * FROM contact")
    List<MessagesOfContact> getMessagesOfContact();

    @Query("SELECT * FROM contact WHERE id = (SELECT MAX(id) FROM contact)")
    Contact getIDMax();

//    @Transaction
    @Insert  //get list of contact
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);

    @Delete
    void deleteMany(List<Contact> contacts);

    @Insert  //get list of contact
    void insertMany(List<Contact> contacts);



//
//    @Insert
//    void insertMessages(List<Message> messages);

}
