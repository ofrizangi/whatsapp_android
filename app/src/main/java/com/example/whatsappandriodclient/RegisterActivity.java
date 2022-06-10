package com.example.whatsappandriodclient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.databinding.ActivityRegisterBinding;
import com.example.whatsappandriodclient.entities.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private static final int IMAGE_PICK_CODE =1000;
    private String userName = "";


    // this is not working - need to find the right source
    private Uri image = Uri.parse("android.resource://com.example.whatsappandriodclient/drawable/avatar.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

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
                    this.userName = binding.username.getText().toString();
                    String nickName = binding.nickname.getText().toString();
                    String password = binding.password.getText().toString();
                    String confirm = binding.confirm.getText().toString();
                    String image = "";

                    if(checkPassAndUser(password, confirm, userName)) {
                        User user = new User(userName, nickName, password, image, new ArrayList<>());
                        addUser(user);
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



    public void setImage(Uri image) {
        this.image = image;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode,data );
        if (requestCode == IMAGE_PICK_CODE){
            if (resultCode == Activity.RESULT_OK && data!=null) {
                Uri uri = data.getData();
                setImage(uri);
                Log.i("image", uri.toString());
                binding.imageView.setImageURI(uri);
//                String[] info = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(uri, info, null, null, null);
//                if(cursor != null) {
//                    cursor.moveToFirst();
//                    Bitmap bitmap = null;
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    // Set the ImageView with the bitmap of the image
//                    binding.imageView.setImageBitmap(bitmap);
//                }
            }
        }

    }



    public void addUser(User user) {
        Call<String> call = UserAPI.getInstance().getMyApi().createUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
                    if(token.compareTo("false") == 0){
                            AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
                            alert.setTitle("error");
                            alert.setMessage("user name already exist");
                            alert.create().show();
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("token", token);
//                            intent.putExtra("image", image);
                            userName = "";
//                            image = null;
                            startActivity(intent);
                        }

                    Log.i("in response", "token");
                }

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });

    }

    //    private void getUsers(){
//        Call<List<User>> call = UserAPI.getInstance().getMyApi().getUser();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                Log.i("in response", "succeed");
//
//                List<User> post = response.body();
//                Log.i("sdsd", post.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<User>> call, Throwable t) {
//                Log.i("in fail", "fail");
//
//            }
//        });
//    }


}