package com.example.whatsappandriodclient.entities;


import java.util.ArrayList;
import java.util.List;
//import
//@Entity
public class User {
    private String userName;
    private String nickName;
    private String password;
    private String image;
    private List<Contact> contacts;

    public User(String userName,  String nickName, String password, String image) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.image = image;
        this.contacts = new ArrayList<>();
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
