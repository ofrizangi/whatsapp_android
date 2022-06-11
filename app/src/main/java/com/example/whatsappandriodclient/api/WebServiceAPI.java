package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.entities.ContactToAdd;
//import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.entities.UserLogin;
import com.example.whatsappandriodclient.entities.UserRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebServiceAPI {

     @GET("user/get")
     Call<List<UserRegister>> getUser();

     @POST("user/register")
     Call<String> createUser(@Body UserRegister user);

     @POST("user/login")
     Call<String> sendUser(@Body UserLogin userLogin);

     @POST("contacts")
     Call<Void> addContact(@Header("authorization") String token, @Body ContactToAdd contactToAdd);



 }


