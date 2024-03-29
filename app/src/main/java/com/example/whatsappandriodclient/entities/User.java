package com.example.whatsappandriodclient.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
//import
@Entity
public class User {
    @NonNull
    @PrimaryKey
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


    public User(@NonNull String userName,  String nickName, String password, String image) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.image = image;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(@NonNull  String userName) {
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



}
