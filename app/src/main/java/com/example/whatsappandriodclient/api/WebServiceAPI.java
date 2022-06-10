package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.entities.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {

     @GET("user/get")
     Call<List<User>> getUser();

     @POST("user/register")
     Call<String> createUser(@Body User user);

     @POST("user/login")
     Call<String> sendUser(@Body UserLogin userLogin);



 }


