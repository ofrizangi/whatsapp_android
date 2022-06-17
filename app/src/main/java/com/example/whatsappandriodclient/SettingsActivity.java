package com.example.whatsappandriodclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatsappandriodclient.databinding.ActivitySettingsBinding;
import com.example.whatsappandriodclient.entities.App;
import com.example.whatsappandriodclient.viewmodels.AppViewModel;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private AppViewModel viewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AppViewModel.class);


        List<App> list = viewModel.getServer();
        App oldServer = viewModel.getServer().get(0);
        App oldServer2= viewModel.getServer().get(1);
        binding.changeServer.setText(oldServer.getServer());
        binding.changeServer2.setText(oldServer2.getServer());
        binding.buttonChange.setOnClickListener(v->{
            App myServer = new App(binding.changeServer.getText().toString());
            App myServer2 = new App(binding.changeServer2.getText().toString());
            viewModel.deleteServer(oldServer);
            viewModel.deleteServer(oldServer2);
            viewModel.insertServer(myServer, myServer2);
        });


        binding.link.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            startActivity(intent);
        });




    }
}