package com.example.whatsappandriodclient.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
//    @ColumnInfo(name= "id")
    private int id;
    @SerializedName("contactUserName")
//    @ColumnInfo(name= "contactUserName")
    private String contactUserName;
    @SerializedName("contactNickName")
//    @ColumnInfo(name= "contactNickName")
    private String contactNickName;
    @SerializedName("server")
//    @ColumnInfo(name= "server")
    private String server;

    public Contact(int id, String contactUserName, String contactNickName, String server) {
        this.id = id;
        this.contactUserName = contactUserName;
        this.contactNickName = contactNickName;
        this.server = server;
    }

    public int getId() {
        return id;
    }

    public String getContactUserName() {
        return contactUserName;
    }

    public String getContactNickName() {
        return contactNickName;
    }

    public String getServer() {
        return server;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setContactUserName(String contactUserName) {
        this.contactUserName = contactUserName;
    }

    public void setContactNickName(String contactNickName) {
        this.contactNickName = contactNickName;
    }

    public void setServer(String server) {
        this.server = server;
    }

}