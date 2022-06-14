package com.example.whatsappandriodclient.api;

import android.util.Log;

import com.example.whatsappandriodclient.objectAPI.Transfer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TransferAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;


    public TransferAPI(String contactServer) {
        String port = contactServer.substring(9);
        final String url = "http://10.0.2.2" + port + "/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public WebServiceAPI getMyApi() {
        return webServiceAPI;
    }

    public void transferMessage(Transfer transfer) {
        Call<Void> call = webServiceAPI.transferMessage(transfer);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                }
                else{
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });

    }

}






