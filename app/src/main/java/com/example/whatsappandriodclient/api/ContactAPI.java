package com.example.whatsappandriodclient.api;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;

import com.example.whatsappandriodclient.AddContactActivity;
import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.entities.ContactToAdd;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ContactAPI {

    private static ContactAPI instance = null;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public ContactAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5271/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public static synchronized ContactAPI getInstance() {
        if (instance == null) {
            instance = new ContactAPI();
        }
        return instance;
    }

    public WebServiceAPI getMyApi() {
        return webServiceAPI;
    }

    public void addContact(ContactToAdd contact, String token) {
        Call<Void> call = this.webServiceAPI.addContact("Bearer " + token, contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
//                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//                    ChatListActivity.getInstance().startActivity(Intent.createChooser(intent, "Share"));
//                        AddContactActivity.navigate();
                    Intent myIntent = new Intent(AddContactActivity.getInstance(), ChatListActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("token", token);
                    AddContactActivity.getInstance().startActivity(myIntent);
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddContactActivity.getInstance());
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