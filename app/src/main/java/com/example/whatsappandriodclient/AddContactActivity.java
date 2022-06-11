package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.databinding.ActivityAddContactBinding;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.ContactToAdd;
import com.example.whatsappandriodclient.viewmodels.ContactsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private ContactsViewModel viewModel;

    private static AddContactActivity sInstance;


    public static AddContactActivity getInstance() {
        return sInstance;
    }

    private LocalDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        Log.i("chat", intent.getStringExtra("token"));

        db = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();


        binding.addcontact.setOnClickListener(v -> {

            Contact c = new Contact("sivan", "sss", "local");

            contactDao.insert(c);
            finish();
            String nickName = binding.nickname.getText().toString();
            String username = binding.username.getText().toString();
            String server = binding.server.getText().toString();
            viewModel.addContact(token, new ContactToAdd(username, nickName, server));

        });


    }


}