package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.R;
import com.example.whatsappandriodclient.MyApplication;
import com.example.whatsappandriodclient.api.WebServiceAPI;
import com.example.whatsappandriodclient.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                    //.baseUrl("https://10.0.2.2:7271/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void post(User user) {
        webServiceAPI.createUser(user);

    }


    public void get() {
//        Retrofit retrofit = RetrofitFactory.getRetrofit();
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<User>> call = webServiceAPI.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                List<User> post = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}
