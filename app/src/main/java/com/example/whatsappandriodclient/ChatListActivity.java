package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.whatsappandriodclient.adapters.ContactListAdapter;
import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.databinding.ActivityChatListBinding;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.viewmodels.ContactsViewModel;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private ActivityChatListBinding binding;
    private ContactsViewModel viewModel;
    private LocalDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Log.i("chat", intent.getStringExtra("token"));
        Log.i("chat", intent.getStringExtra("userName"));

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

/***
        final ContactListAdapter adapter = new ContactListAdapter(this);
        binding.listcontacts.setAdapter(adapter);
        binding.listcontacts.setLayoutManager(new LinearLayoutManager(this));



        // observing the view model, and when we get called updating the adapter
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
                }
        );

*/


//        LocalDB db = Room.databaseBuilder(getApplicationContext(), LocalDB.class,  "contact").
//                allowMainThreadQueries().build();
//        ContactDao contactDao = db.contactDao();



        db = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "ContactDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDao();


//        Contact c= new Contact(1, "sivan", "sss", "local");
//        contactDao.insert(c);


//        List<Contact> con = contactDao.index();
//        Log.i("room" , contactDao.index().toString());

        binding.addcontact.setOnClickListener(v -> {
                    Intent myIntent = new Intent(getApplicationContext(), AddContactActivity.class);
                    startActivity(myIntent);
        }

        );
//      binding.listcontacts
        contacts = contactDao.index();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        binding.listcontacts.setAdapter(adapter);
//        final ContactListAdapter adapter = new ContactListAdapter(this);
//        binding.listcontacts.setAdapter(adapter);
//        binding.listcontacts.setLayoutManager(new LinearLayoutManager(this));


//
//         //observing the view model, and when we get called updating the adapter
//        viewModel.get().observe(this, contacts -> {
//                    adapter.setContacts(contacts);
//                }
//        );

    }
}