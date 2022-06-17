package com.example.whatsappandriodclient.api;

import android.app.AlertDialog;
import android.util.Log;

import com.example.whatsappandriodclient.ChatActivity;
import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.dao.AppDao;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.entities.App;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.objectAPI.Transfer;

import java.util.List;

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
        LocalDB localDB = LocalDB.getDatabase(ChatActivity.getInstance());
        AppDao appDao = localDB.appDao();
        List<App> list = appDao.index();
        retrofit = new Retrofit.Builder()
                .baseUrl(list.get(0).getServer())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public static synchronized MessageAPI getInstance() {
        return new MessageAPI();
    }

    public void sendMessage(SendMessage message, String token, String contactName, ContactDao contactDao, String id, String userName) {
        Call<Void> call = this.webServiceAPI.sendMessage("Bearer " + token, contactName, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                boolean r = response.isSuccessful();
                if(response.isSuccessful()) {
                    Contact contact = contactDao.get(id);
                    TransferAPI transferAPI = new TransferAPI(contact.getServer());
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
