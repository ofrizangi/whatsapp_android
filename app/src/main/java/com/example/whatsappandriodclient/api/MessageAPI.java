package com.example.whatsappandriodclient.api;

import android.app.AlertDialog;
import android.util.Log;

import com.example.whatsappandriodclient.ChatActivity;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.objectAPI.Transfer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MessageAPI {

    private static MessageAPI instance = null;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5271/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public static synchronized MessageAPI getInstance() {
        if (instance == null) {
            instance = new MessageAPI();
        }
        return instance;
    }

    public void sendMessage(SendMessage message, String token, String contactName, ContactDao contactDao, int id, String userName) {
        Call<Void> call = this.webServiceAPI.sendMessage("Bearer " + token, contactName, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {

                    // if the message sent sucessfuly we want to transfer it to the other client
                    // do it by id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                    Contact contact = contactDao.get(id);
                    TransferAPI transferAPI = new TransferAPI("localhost:5271");
                    Transfer transfer = new Transfer(userName, contactName, message.getContent());
                    transferAPI.transferMessage(transfer);
                    Log.i("in send", "succeed");
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChatActivity.getInstance());
                    alert.setTitle("error");
                    alert.setMessage("something went wrong");
                    alert.create().show();
                }
                Log.i("in response", "succeed");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });

    }
}
