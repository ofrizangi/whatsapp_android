package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
//        register_link = findViewById(R.id.link);


        binding.link.setOnClickListener(v ->{
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
            }
        );



    }

//    public void aaa(View view) {
//        Log.i("hell", "h");
//        Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
//    }
}