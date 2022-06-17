package com.example.whatsappandriodclient.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app")
public class App {

    @PrimaryKey
    @NonNull
    String server;

    public App(@NonNull String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setServer(@NonNull String server) {
        this.server = server;
    }
}
