package com.example.whatsappandriodclient.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactsOfUser;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.entities.MessagesOfContact;
import com.example.whatsappandriodclient.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> index();

    @Query("SELECT * FROM user WHERE userName = :userName")
    User get(String userName);

    @Transaction
    @Query("SELECT * FROM user")
    List<ContactsOfUser> getContactsOfUser();

    //    @Transaction
    @Insert
    //get list of contact
    void insert(User... user);

    @Update
    void update(User... user);

    @Delete
    void delete(User... user);
}
