package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whatsappandriodclient.adapters.ContactListAdapter;
import com.example.whatsappandriodclient.databinding.ActivityChatListBinding;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.objectAPI.TokenApplication;
import com.example.whatsappandriodclient.viewmodels.UserViewModel;
import com.example.whatsappandriodclient.viewmodels.UserViewModelFactory;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements ContactListAdapter.OnContactListener {

    private ActivityChatListBinding binding;
    private UserViewModel viewModel;

    private static ChatListActivity sInstance;


    public static ChatListActivity getInstance(){
        return sInstance;
    }


    public List<Contact> getContacts() {
        return contacts;
    }

//    public void setContacts(List<Contact> contacts) {
//        this.contacts = contacts;
//        adapter.setContacts(contacts);
//
//
//    }

    private List<Contact> contacts;
    ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        adapter = new ContactListAdapter(this, this);

        sInstance = this;
        binding = ActivityChatListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Log.i("chat", intent.getStringExtra("token"));
        if(intent.hasExtra("userName")){
            Log.i("chat", intent.getStringExtra("userName"));
        }



        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatListActivity.this, instanceIdResult -> {
//            String newToken = instanceIdResult.getToken();
            TokenApplication tokenApplication= new TokenApplication(instanceIdResult.getToken());
            viewModel.sendTokenToServer(tokenApplication,  intent.getStringExtra("token"));
          Log.i("token firebase", instanceIdResult.getToken());
        });


        viewModel = new ViewModelProvider(this, new UserViewModelFactory(intent.getStringExtra("userName"))).get(UserViewModel.class);
        viewModel.getAllContacts(intent.getStringExtra("token"));
//        viewModel.getAllContacts(intent.getStringExtra("token"));

        binding.addcontact.setOnClickListener(v -> {
            Intent myIntent = new Intent(getApplicationContext(), AddContactActivity.class);
                    myIntent.putExtra("userName", intent.getStringExtra("userName"));
                    myIntent.putExtra("token", intent.getStringExtra("token"));

                    startActivity(myIntent);
                }
        );


        contacts = new ArrayList<>();
        binding.listcontacts.setAdapter(adapter);
        binding.listcontacts.setLayoutManager(new LinearLayoutManager(this));


        // observing the view model, and when we get called updating the adapter
        viewModel.get().observe(this, contacts -> {
                    adapter.setContacts(contacts);
                }
        );


    }


    @Override
    public void onResume(){
        super.onResume();
        viewModel.setContactView();
    }



    @Override
    public void onContactClick(int position) {
        Intent myIntent = getIntent();
        Contact contact = viewModel.getAContact(position);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("contactNickName" , contact.getContactNickName());
        intent.putExtra("contactUserName" , contact.getContactUserName());
        intent.putExtra("contactId" , contact.getKey());
        intent.putExtra("token" , myIntent.getStringExtra("token"));
        intent.putExtra("userName" , myIntent.getStringExtra("userName"));
        startActivity(intent);
    }



}