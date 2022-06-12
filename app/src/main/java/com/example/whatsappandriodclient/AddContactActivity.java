package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.dao.MessageDao;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.databinding.ActivityAddContactBinding;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.entities.ContactsOfUser;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.viewmodels.ContactViewModel;

import java.util.Date;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private ContactViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        Log.i("chat", intent.getStringExtra("token"));

        db = LocalDB.getDatabase(getApplicationContext());
//        contactDao = db.contactDao();
        MessageDao  messageDao= db.messageDao();
        UserDao userDao = db.userDao();
        messageDao.insert(new Message("hello", new Date(), true, 1));
        List<Message> messages = messageDao.index();
        Log.i("hek", "hek");

        binding.addcontact.setOnClickListener(v -> {

            User user = new User(binding.nickname.getText().toString(),"ofri","123", "img");
            userDao.insert(user);

//            Contact c = new Contact(binding.username.getText().toString(), "sss", "local", "Ofri");
//            contactDao.insert(c);
//            Contact c2 = new Contact(binding.username.getText().toString(), "sss", "local", "ofri");
//            contactDao.insert(c2);

//            int id = c.getId();

//            Message m = new Message("A","A",true,id);
//            messageDao.insert(m);
            List<ContactsOfUser> contact = userDao.getContactsOfUser();
            List<Contact> contacts1 = contactDao.index();

//            List<MessagesOfContact> messages = contactDao.getMessagesOfContact();

            finish();
            String nickName = binding.nickname.getText().toString();
            String username = binding.username.getText().toString();
            String server = binding.server.getText().toString();
            viewModel.addContact(token, new ContactToAdd(username, nickName, server), intent.getStringExtra("userName"));

        });


    }


}