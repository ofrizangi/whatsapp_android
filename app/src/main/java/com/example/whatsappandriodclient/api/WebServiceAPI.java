package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.objectAPI.UserRegister;
import com.example.whatsappandriodclient.objectAPI.ContactGet;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

     @GET("user/get")
     Call<List<UserRegister>> getUser();

     @POST("user/register")
     Call<String> createUser(@Body UserRegister user);

     @POST("user/login")
     Call<String> sendUser(@Body UserLogin userLogin);

     @POST("contacts")
     Call<Void> addContact(@Header("authorization") String token, @Body ContactToAdd contactToAdd);

     @GET("contacts")
     Call<List<ContactGet>> getAllContacts(@Header("authorization") String token);

    @POST("contacts/{id}/messages")
    Call<Void> sendMessage(@Header("authorization") String token, @Path(value = "id") String contactUserName, @Body SendMessage message);


 }


