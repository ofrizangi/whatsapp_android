package com.example.whatsappandriodclient.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//, primaryKeys = {"contactUserName","userID"}
@Entity(tableName = "contact")
public class Contact {

    @PrimaryKey(autoGenerate = true)
   //@SerializedName("id")
    //@ColumnInfo(name= "id")
      private int id;
//    @SerializedName("contactUserName")
//    @ColumnInfo(name= "contactUserName")
    @NonNull
    private String contactUserName;
//    @SerializedName("contactNickName")
//    @ColumnInfo(name= "contactNickName")
    private String contactNickName;
//    @SerializedName("server")
//    @ColumnInfo(name= "server")
    private String server;
    @NonNull
    private String userID;


    private String lastDate;

    private String lastMessage;



    public Contact( @NonNull String contactUserName, String contactNickName, String server, @NonNull String userID, String lastMessage, String lastDate, int ID) {
        this.lastMessage = lastMessage;
        this.lastDate = lastDate;
        this.contactUserName = contactUserName;
        this.contactNickName = contactNickName;
        this.server = server;
        this.userID = userID;
        this.id = ID;
    }
    public Contact(){}

    public String getUserID() {
        return userID;
    }

    public void setUserID(@NonNull String userID) {
        this.userID = userID;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getLastMessage() {
        return lastMessage;
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


    public void setContactUserName(@NonNull String contactUserName) {
        this.contactUserName = contactUserName;
    }

    public void setContactNickName(String contactNickName) {
        this.contactNickName = contactNickName;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "Contact{" +
                ", contactUserName='" + contactUserName + '\'' +
                ", contactNickName='" + contactNickName + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
