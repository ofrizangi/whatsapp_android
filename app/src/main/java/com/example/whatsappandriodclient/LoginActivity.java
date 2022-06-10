package com.example.whatsappandriodclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.databinding.ActivityLoginBinding;
import com.example.whatsappandriodclient.entities.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.link.setOnClickListener(v ->{
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
            }
        );

        binding.login.setOnClickListener(v ->{
                    this.userName = binding.username.getText().toString();
                    String password = binding.password.getText().toString();
                    UserLogin myUser = new UserLogin(userName, password);
                    sendUser(myUser);
                }
        );
    }


    public void sendUser(UserLogin user) {
        Call<String> call = UserAPI.getInstance().getMyApi().sendUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                Log.i("in response", token);
                if(token.compareTo("false") == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setTitle("error");
                    alert.setMessage("user name or password are incorrect");
                    alert.create().show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("token", token);
                    userName = "";
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });

    }

}