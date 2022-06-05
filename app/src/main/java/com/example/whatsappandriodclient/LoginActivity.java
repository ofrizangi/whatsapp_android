package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


//    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setContentView(R.layout.activity_login);

        Button register_link = findViewById(R.id.link);

        register_link.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }

//    public void aaa(View view) {
//        Log.i("hell", "h");
//        Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
//    }
}