package com.example.whatsappandriodclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatsappandriodclient.databinding.ActivityRegisterBinding;
import com.example.whatsappandriodclient.objectAPI.UserRegister;
import com.example.whatsappandriodclient.viewmodels.AppViewModel;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private static final int IMAGE_PICK_CODE =1000;
    private AppViewModel viewModel;
    private static RegisterActivity sInstance;

    public static RegisterActivity getInstance(){
        return sInstance;
    }

    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sInstance = this;
        viewModel = new ViewModelProvider(this).get(AppViewModel.class);

        binding.link.setOnClickListener(v ->{
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                    startActivity(intent);
                }
        );

        binding.image.setOnClickListener(v ->
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Open Gallery"), IMAGE_PICK_CODE);
        });

        binding.register.setOnClickListener(v ->{
                    String userName = binding.username.getText().toString();
                    String nickName = binding.nickname.getText().toString();
                    String password = binding.password.getText().toString();
                    String confirm = binding.confirm.getText().toString();
                    String image = this.image;

                    if(checkPassAndUser(password, confirm, userName)) {
                        UserRegister user = new UserRegister(userName, nickName, password, image, new ArrayList<>());
                        viewModel.addUser(user);
                    }
                }
        );


    }

    private boolean checkPassAndUser(String password, String confirm, String userName){
        if(password.compareTo(confirm) != 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("error");
            alert.setMessage("your passwords do not match");
            alert.create().show();
            return false;
        }
        if(!password.matches("^(\\d+[a-zA-Z]|[a-zA-Z]+\\d)(\\d|[a-zA-Z])*") || password.length() < 8){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("error");
            alert.setMessage("password must be at least 8 characters, must contain at least one number and letter");
            alert.create().show();
            return false;
        }
        if(userName.equals("")){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("error");
            alert.setMessage("not a valid user name");
            alert.create().show();
            return false;
        }
        return true;
    }



    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data );
        if (requestCode == IMAGE_PICK_CODE){
            if (resultCode == Activity.RESULT_OK && data!=null) {
                Uri uri = data.getData();
                setImage(uri.toString());
                Uri uri2 = Uri.parse(uri.toString());
                binding.imageView.setImageURI(uri2);
            }
        }

    }



}