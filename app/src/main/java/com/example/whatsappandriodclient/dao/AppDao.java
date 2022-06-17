package com.example.whatsappandriodclient.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsappandriodclient.entities.App;

import java.util.List;
@Dao
public interface AppDao {

        @Query("SELECT * FROM app")
        List<App> index();


        @Insert
        void insert(App... apps);

        @Update
        void update(App... apps);

        @Delete
        void delete(App... apps);

}
