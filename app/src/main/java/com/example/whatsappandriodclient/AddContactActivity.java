package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.databinding.ActivityAddContactBinding;
import com.example.whatsappandriodclient.entities.Contact;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private LocalDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();

        binding.addcontact.setOnClickListener(v ->{
            Contact c= new Contact(9, "sivan", "sss", "local");
            contactDao.insert(c);
//            Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
//            startActivity(intent);
            finish();
                }
        );
    }
}