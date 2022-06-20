package com.example.whatsappandriodclient;


import static androidx.room.Room.databaseBuilder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.whatsappandriodclient.dao.AppDao;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.Converters;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.App;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.entities.User;


@Database(entities = {Contact.class, Message.class, User.class, App.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LocalDB extends RoomDatabase {

    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();
    public abstract AppDao appDao();

    private static volatile LocalDB INSTANCE;

    public static LocalDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = databaseBuilder(context,
                                    LocalDB.class, "myNewWhatsappDB")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
