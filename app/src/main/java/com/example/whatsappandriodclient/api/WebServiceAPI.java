package com.example.whatsappandriodclient.api;

//import com.example.whatsappandriodclient.entities.User;

import com.example.whatsappandriodclient.objectAPI.ContactGet;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.GetMessage;
import com.example.whatsappandriodclient.objectAPI.Invitation;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.objectAPI.TokenApplication;
import com.example.whatsappandriodclient.objectAPI.Transfer;
import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.objectAPI.UserRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @GET("contacts/{id}/messages")
    Call<List<GetMessage>> getMessages(@Header("authorization") String token, @Path(value = "id") String contactUserName);

    @POST("invitations")
    Call<Void> inviteContact(@Body Invitation invitation);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Header("authorization") String token, @Path(value = "id") String contactUserName);

    @POST("transfer")
    Call<Void> transferMessage(@Body Transfer transfer);

    //    Call<Void> sendToken(@Header("authorization") String tokenApplication,@Body String tokenUser);
    @POST("firebase")
    Call<Void> sendToken(@Header("authorization") String tokenUser, @Body TokenApplication tokenApplication);
//    Call<Void> sendToken(@Header("authorization") String tokenApplication);


}