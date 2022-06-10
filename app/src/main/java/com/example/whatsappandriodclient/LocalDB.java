package com.example.whatsappandriodclient;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.Contact;

@Database(entities = {Contact.class}, version = 2, exportSchema = false)
public abstract class LocalDB extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static volatile LocalDB INSTANCE;

    public static LocalDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    LocalDB.class, "whatsapp_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
