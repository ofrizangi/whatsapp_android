package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private MessageViewModel viewModel;
    private ContactViewModel viewModelContact;
    private ActivityChatBinding binding;

    private static ChatActivity sInstance;
    MessageListAdapter adapter;

    public static ChatActivity getInstance() {
        return sInstance;
    }
    private List<Message> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MessageListAdapter(this);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sInstance = this;
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        Intent intent = getIntent();

        viewModelContact = new ViewModelProvider(this, new ContactViewModelFactory(intent.getIntExtra("contactId", 0))).get(ContactViewModel.class);

        viewModelContact.updateMessages(intent.getStringExtra("token"), intent.getStringExtra("contactUserName"));

        binding.contactname.setText(intent.getStringExtra("contactNickName"));
        Log.i("token", intent.getStringExtra("token"));

        binding.sendMessage.setOnClickListener(v -> {
                    String content = binding.message.getText().toString();
                    if(!content.equals("")){
                        SendMessage sendMessage = new SendMessage(content);
                        viewModel.addMessage(intent.getStringExtra("token"), sendMessage, intent.getStringExtra("contactUserName"), intent.getIntExtra("contactId", 0), intent.getStringExtra("userName"));
                        binding.message.setText("");
                    }
                }
        );
        messages = new ArrayList<>();

        binding.listMessages.setAdapter(adapter);
        binding.listMessages.setLayoutManager(new LinearLayoutManager(this));
//        List<Message> messages= new ArrayList<>();

        viewModelContact.get().observe(this, messages -> {
                    adapter.setMessages(messages);
                }
        );
    }
}