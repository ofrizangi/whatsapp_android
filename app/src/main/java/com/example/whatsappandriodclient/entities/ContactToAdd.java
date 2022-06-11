package com.example.whatsappandriodclient.entities;

import com.google.gson.annotations.SerializedName;

public class ContactToAdd {

    @SerializedName("id")
    private String contactName;

    @SerializedName("name")
    private String contactNickName;

    @SerializedName("server")
    private String server;

    public ContactToAdd(String contactName, String contactNickName, String server) {
        this.contactName = contactName;
        this.contactNickName = contactNickName;
        this.server = server;
    }


    public String getContactName() {
        return contactName;
    }

    public String getContactNickName() {
        return contactNickName;
    }

    public String getServer() {
        return server;
    }


    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactNickName(String contactNickName) {
        this.contactNickName = contactNickName;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
