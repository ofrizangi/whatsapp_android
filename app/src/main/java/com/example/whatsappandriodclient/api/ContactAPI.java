package com.example.whatsappandriodclient.api;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;

import com.example.whatsappandriodclient.AddContactActivity;
import com.example.whatsappandriodclient.ChatActivity;
import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.GetMessage;
import com.example.whatsappandriodclient.objectAPI.Invitation;
import com.example.whatsappandriodclient.repositories.ContactRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5271/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    public void addContact(ContactToAdd contact, String token, String userName) {
        Call<Void> call = this.webServiceAPI.addContact("Bearer " + token, contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    Intent myIntent = new Intent(AddContactActivity.getInstance(), ChatListActivity.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("token", token);
                    myIntent.putExtra("userName", userName);
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

    public void inviteContact(Invitation invitation, String contactServer) {
//        String server = "http://10.0.2.2:" +  contactServer + "/api/";
//        Retrofit newRetrofit = new Retrofit.Builder()
//                .baseUrl(server)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        WebServiceAPI newWebServer = newRetrofit.create(WebServiceAPI.class);
//        Call<Void> call = newWebServer.inviteContact(invitation);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(response.isSuccessful()) {
//                    Log.i("succed", "s");
//                }
//                else{
//                    AlertDialog.Builder alert = new AlertDialog.Builder(AddContactActivity.getInstance());
//                    alert.setTitle("error");
//                    alert.setMessage("something went wrong");
//                    alert.create().show();
//                }
//            }
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.i("in fail", "fail");
//            }
//        });

    }








    public void getAllMessages(String token, ContactRepository contactRepository, String contactName) {
        Call<List<GetMessage>> call = webServiceAPI.getMessages("Bearer " + token, contactName);
        call.enqueue(new Callback<List<GetMessage>>() {
            @Override
            public void onResponse(Call<List<GetMessage>> call, Response<List<GetMessage>> response) {
                if(response.isSuccessful()) {
                    // do set value when have mutable elements
                    contactRepository.insertMessageToRoom(response.body());
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChatActivity.getInstance());
                    alert.setTitle("error");
                    alert.setMessage("something went wrong");
                    alert.create().show();
                }
            }

            @Override
            public void onFailure(Call<List<GetMessage>> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });
    }
}