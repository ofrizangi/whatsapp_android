package com.example.whatsappandriodclient.api;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;

import com.example.whatsappandriodclient.ChatListActivity;
import com.example.whatsappandriodclient.LoginActivity;
import com.example.whatsappandriodclient.RegisterActivity;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.objectAPI.ContactGet;
import com.example.whatsappandriodclient.objectAPI.UserRegister;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserAPI {

    private static UserAPI instance = null;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {
        retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:5271/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public static synchronized UserAPI getInstance() {
        if (instance == null) {
            instance = new UserAPI();
        }
        return instance;
    }

    public WebServiceAPI getMyApi() {
        return webServiceAPI;
    }


    public void get(UserDao userDao) {
        Call<List<UserRegister>> call = webServiceAPI.getUser();
        call.enqueue(new Callback<List<UserRegister>>() {
            @Override
            public void onResponse(Call<List<UserRegister>> call, Response<List<UserRegister>> response) {
                Log.i("in response", "succeed");

                List<UserRegister> users = response.body();
                List<User> users1 = new ArrayList<>();
                for(UserRegister user: users){
                    users1.add(new User(user.getUserName(), user.getNickName(), user.getPassword(), user.getImage()));
                }
                userDao.insertMany(users1);
//                List<User> my = userDao.index();
//                Log.i("blah", "blah");
            }
            @Override
            public void onFailure(Call<List<UserRegister>> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });
    }


    public void getAllContacts(String token, UserRepository userRepository) {
        Call<List<ContactGet>> call = webServiceAPI.getAllContacts("Bearer " + token);
        final List<Contact>[] contacts = new List[]{new ArrayList<>()};
        call.enqueue(new Callback<List<ContactGet>>() {
            @Override
            public void onResponse(Call<List<ContactGet>> call, Response<List<ContactGet>> response) {
                if(response.isSuccessful()) {
                    List<ContactGet> contactGets = response.body();
                    ChatListActivity.getInstance().setContacts(userRepository.insertContactsToDao(contactGets));
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChatListActivity.getInstance());
                    alert.setTitle("error");
                    alert.setMessage("something went wrong");
                    alert.create().show();
                }
            }

            @Override
            public void onFailure(Call<List<ContactGet>> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });
    }


    public void addUser(UserRegister user) {
        Call<String> call = UserAPI.getInstance().getMyApi().createUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
                    if(token.compareTo("false") == 0){
                        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.getInstance());
                        alert.setTitle("error");
                        alert.setMessage("user name already exist");
                        alert.create().show();
                    }
                    else{
                        Intent intent = new Intent(RegisterActivity.getInstance(), ChatListActivity.class);
                        intent.putExtra("userName", user.getUserName());
                        intent.putExtra("token", token);
                        RegisterActivity.getInstance().startActivity(intent);
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


    public void sendUser(UserLogin user) {
        Call<String> call = UserAPI.getInstance().getMyApi().sendUser(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                if(token.compareTo("false") == 0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.getInstance());
                    alert.setTitle("error");
                    alert.setMessage("user name or password are incorrect");
                    alert.create().show();
                }
                else{
                    Intent intent = new Intent(LoginActivity.getInstance(), ChatListActivity.class);
                    intent.putExtra("userName", user.getUserName());
                    intent.putExtra("token", token);
                    LoginActivity.getInstance().startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("in fail", "fail");
            }
        });

    }

}
