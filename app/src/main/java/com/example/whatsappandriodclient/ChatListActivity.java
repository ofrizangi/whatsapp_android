package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whatsappandriodclient.adapters.ContactListAdapter;
//import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.databinding.ActivityChatListBinding;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.viewmodels.UserViewModel;
import com.example.whatsappandriodclient.viewmodels.UserViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements ContactListAdapter.OnContactListener {

    private ActivityChatListBinding binding;
    private UserViewModel viewModel;


    private static ChatListActivity sInstance;


    public static ChatListActivity getInstance(){
        return sInstance;
    }

    private LocalDB db;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Log.i("chat", intent.getStringExtra("token"));
        if(intent.hasExtra("userName")){
            Log.i("chat", intent.getStringExtra("userName"));
        }

        viewModel = new ViewModelProvider(this, new UserViewModelFactory(intent.getStringExtra("userName"))).get(UserViewModel.class);


        binding.addcontact.setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), AddContactActivity.class);
                    myIntent.putExtra("userName", intent.getStringExtra("userName"));
                    myIntent.putExtra("token", intent.getStringExtra("token"));

                    startActivity(myIntent);
                }
        );

        this.contacts = new ArrayList<>();




        final ContactListAdapter adapter = new ContactListAdapter(this, this);
        binding.listcontacts.setAdapter(adapter);
        binding.listcontacts.setLayoutManager(new LinearLayoutManager(this));


        // observing the view model, and when we get called updating the adapter
        viewModel.get().observe(this, contacts -> {
                    adapter.setContacts(contacts);
                }
        );



//        ArrayAdapter<Contact> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
//        binding.listcontacts.setAdapter(adapter2);


//        Contact c= new Contact(1, "sivan", "sss", "local");
//        contactDao.insert(c);


//        List<Contact> con = contactDao.index();
//        Log.i("room" , contactDao.index().toString());

        viewModel.getAllContacts(intent.getStringExtra("token"));
    }

    @Override
    public void onContactClick(int position) {
        Intent myIntent = getIntent();
        Contact contact = viewModel.getAContact(position);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("contactNickName" , contact.getContactNickName());
        intent.putExtra("contactUserName" , contact.getContactUserName());
        intent.putExtra("contactId" , contact.getId());
        intent.putExtra("token" , myIntent.getStringExtra("token"));
        startActivity(intent);
    }



}