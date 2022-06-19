package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.whatsappandriodclient.adapters.MessageListAdapter;
import com.example.whatsappandriodclient.databinding.ActivityChatBinding;
import com.example.whatsappandriodclient.entities.Message;
import com.example.whatsappandriodclient.objectAPI.SendMessage;
import com.example.whatsappandriodclient.viewmodels.ContactViewModel;
import com.example.whatsappandriodclient.viewmodels.ContactViewModelFactory;
import com.example.whatsappandriodclient.viewmodels.MessageViewModel;
import com.example.whatsappandriodclient.viewmodels.UserViewModel;
import com.example.whatsappandriodclient.viewmodels.UserViewModelFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private MessageViewModel viewModel;
    private ContactViewModel viewModelContact;
    private UserViewModel userViewModel;
    private ActivityChatBinding binding;
    private static ChatActivity sInstance;
    MessageListAdapter adapter;

    public static ChatActivity getInstance() {
        return sInstance;
    }

    public static void setsInstance(){
        sInstance = null;
    }

    private List<Message> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        adapter = new MessageListAdapter(this);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sInstance = this;
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        Intent intent = getIntent();

        viewModelContact = new ViewModelProvider(this, new ContactViewModelFactory(intent.getStringExtra("contactId"))).get(ContactViewModel.class);

        userViewModel = new ViewModelProvider(this, new UserViewModelFactory(intent.getStringExtra("userName"))).get(UserViewModel.class);

        viewModelContact.updateMessages(intent.getStringExtra("token"), intent.getStringExtra("contactUserName"));


        binding.contactname.setText(intent.getStringExtra("contactNickName"));

        binding.sendMessage.setOnClickListener(v -> {
                    String content = binding.message.getText().toString();
                    if(!content.equals("")){
                        SendMessage sendMessage = new SendMessage(content);
                        viewModel.addMessage(intent.getStringExtra("token"), sendMessage, intent.getStringExtra("contactUserName"), intent.getStringExtra("contactId"), intent.getStringExtra("userName"));
                        binding.message.setText("");
                        Message message = new Message(sendMessage.getContent(), new Date(), true, intent.getStringExtra("contactId"));
                        viewModelContact.addMessageToView(message);
                        userViewModel.addContactToDao(intent.getStringExtra("contactId"), intent.getStringExtra("token"),intent.getStringExtra("contactUserName") );
                    }
                }
        );
        messages = new ArrayList<>();

        binding.listMessages.setAdapter(adapter);
        binding.listMessages.setLayoutManager(new LinearLayoutManager(this));

        viewModelContact.get().observe(this, messages -> {
                    adapter.setMessages(messages);
                }
        );
    }


    public boolean updateContactFirebase(String contactName, String content, String Id){
        Intent intent = getIntent();
        String myContactName = intent.getStringExtra("contactUserName");
        String contactId =  intent.getStringExtra("contactId");
        Message message1 = new Message(content, new Date(), false, contactId);
        if(myContactName.equals(contactName)){
            viewModel.addMessageToDao(message1,contactId, intent.getStringExtra("token"), contactName );
            viewModelContact.addMessageToView(message1);
            userViewModel.addContactToDao(Id,  intent.getStringExtra("token"), contactName);
            return true;
        }
        viewModel.addMessageToDao(message1,Id , intent.getStringExtra("token"), contactName );
        userViewModel.addContactToDao(Id,  intent.getStringExtra("token"), contactName);
        return false;
    }


}