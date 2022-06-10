package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.addcontact.setOnClickListener(v ->{

                Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
                startActivity(intent);
                }
        );
    }
}