package com.example.whatsappandriodclient.entities;


import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
//import
@Entity(tableName="users")
public class User {
    @SerializedName("userName")
    @Expose
    private String userName;
    @Expose
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts;

    public User(String userName,  String nickName, String password, String image, List<Contact> contacts) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.image = image;
        this.contacts = contacts;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
