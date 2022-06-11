package com.example.whatsappandriodclient.api;

import android.util.Log;

import com.example.whatsappandriodclient.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserAPI {

    private static UserAPI instance = null;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {
        retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5271/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public static synchronized UserAPI getInstance() {
        if (instance == null) {
            instance = new UserAPI();
        }
        return instance;
    }

    public WebServiceAPI getMyApi() {
        return webServiceAPI;
    }


    public void get() {

        Call<List<User>> call = webServiceAPI.getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("in response", "succeed");

                List<User> post = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("in fail", "fail");

            }
        });
    }

}
