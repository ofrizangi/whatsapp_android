package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.databinding.ActivityChatBinding;
import com.example.whatsappandriodclient.entities.SendMessage;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        binding.contactname.setText(intent.getStringExtra("contactName"));

        binding.sendMessage.setOnClickListener(v ->{
                    String content = binding.message.getText().toString();
                    SendMessage sendMessage = new SendMessage(content);
                }
        );
    }
}