package com.example.whatsappandriodclient.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE id = :id")
    Message get(int id);

    //    @Transaction
    @Insert
    //get list of contact
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);

}




