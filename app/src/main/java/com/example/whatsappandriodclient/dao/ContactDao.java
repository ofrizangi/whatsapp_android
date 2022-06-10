package com.example.whatsappandriodclient.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsappandriodclient.entities.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * from contact")
    List<Contact> index();

    @Query("SELECT * from contact WHERE id = :id")
    Contact get(int id);

//    @Transaction
    @Insert
    
    void insert(Contact...contacts);

    @Update
    void update(Contact...contacts);

    @Delete
    void delete(Contact...contacts);


//    @Insert
//    void insertMessages(List<Message> messages);

}
