package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.databinding.ActivityRegisterBinding;
import com.example.whatsappandriodclient.entities.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.link.setOnClickListener(v ->{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    startActivity(intent);
                }
        );
        binding.login.setOnClickListener(v ->{
                    String userName = binding.username.getText().toString();
                    String nickName = binding.nickname.getText().toString();
                    String password = binding.password.getText().toString();
                    String image = "";
//                    Log.i("in9999", userName);
//                    Log.i("in9999", nickName);
//                    Log.i("in9999", password);

                    User user = new User(userName, nickName,password, image);
                    UserAPI userAPI = new UserAPI();
//                    userAPI.post(user);
                    userAPI.get();

//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(intent);
                }
        );


    }
}