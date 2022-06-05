package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

     @GET("user/get")
     Call<List<User>> getUser();

     @POST("user/register")
     Call<Void> createUser(@Body User user);


 }


