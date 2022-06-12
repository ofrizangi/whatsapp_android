package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatsappandriodclient.databinding.ActivityLoginBinding;
import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.viewmodels.AppViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private static LoginActivity sInstance;
    private AppViewModel viewModel;


    public static LoginActivity getInstance(){
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        sInstance = this;
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        viewModel.updateUsers();

        binding.link.setOnClickListener(v ->{
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
            }
        );

        binding.login.setOnClickListener(v ->{
                    String userName = binding.username.getText().toString();
                    String password = binding.password.getText().toString();
                    UserLogin myUser = new UserLogin(userName, password);
                    viewModel.sendUser(myUser);
                }
        );
    }



}