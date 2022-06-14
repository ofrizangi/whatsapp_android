package com.example.whatsappandriodclient.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private Date time;
    private boolean sent;

    private String contactKey;


    public Message(String content, Date time, boolean sent, String contactKey) {
        this.time = time;
        this.content = content;
        this.sent = sent;
        this.contactKey = contactKey;
    }

    public String getContactKey() {
        return contactKey;
    }

    public void setContactKey(String contactKey) {
        this.contactKey = contactKey;
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
