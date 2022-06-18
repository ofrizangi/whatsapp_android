package com.example.whatsappandriodclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.databinding.ActivityAddContactBinding;
import com.example.whatsappandriodclient.entities.App;
import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.Invitation;
import com.example.whatsappandriodclient.viewmodels.AppViewModel;
import com.example.whatsappandriodclient.viewmodels.ContactViewModel;

import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private ContactViewModel viewModel;
    private AppViewModel viewModelApp;


    private static AddContactActivity sInstance;


    public static AddContactActivity getInstance() {
        return sInstance;
    }

    private LocalDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        sInstance = this;
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        viewModelApp = new ViewModelProvider(this).get(AppViewModel.class);
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        Log.i("chat", intent.getStringExtra("token"));

        db = LocalDB.getDatabase(getApplicationContext());


        binding.addcontact.setOnClickListener(v -> {

            User user = new User(binding.nickname.getText().toString(),"ofri","123", "img");
//            userDao.insert(user);

//            Contact c = new Contact(binding.username.getText().toString(), "sss", "local", "Ofri");
//            contactDao.insert(c);
//            Contact c2 = new Contact(binding.username.getText().toString(), "sss", "local", "ofri");
//            contactDao.insert(c2);

//            int id = c.getId();

//            Message m = new Message("A","A",true,id);
//            messageDao.insert(m);
//            List<ContactsOfUser> contact = userDao.getContactsOfUser();
//            List<Contact> contacts1 = contactDao.index();

//            List<MessagesOfContact> messages = contactDao.getMessagesOfContact();

            String nickName = binding.nickname.getText().toString();
            String username = binding.username.getText().toString();
            String server = binding.server.getText().toString();
            if(server.length() < 9){
                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                alert.setTitle("error");
                alert.setMessage("server address is to short");
                alert.create().show();
            }
            List<App> list = viewModelApp.getServer();
            Invitation invitation = new Invitation(intent.getStringExtra("userName"), username, list.get(1).getServer());
            viewModel.addContact(token, new ContactToAdd(username, nickName, server), intent.getStringExtra("userName"), invitation);

//            viewModel.inviteContact(invitation, server,intent.getStringExtra("token"), username );



        });


    }


}