package com.example.whatsappandriodclient.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    //@SerializedName("id")
    private int id;

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    private int contactID;
    //@SerializedName("content")
    private String content;
    //@SerializedName("time")
    private Date time;
    //@SerializedName("sent")
    private boolean sent;

    public Message(String content, Date time, boolean sent, int contactID) {
        this.time = time;
        this.content = content;
        this.sent = sent;
        this.contactID = contactID;
    }


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }

    public boolean isSent() {
        return sent;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

}
