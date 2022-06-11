package com.example.whatsappandriodclient.repositories;

import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.LoginActivity;
import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.entities.UserLogin;
import com.example.whatsappandriodclient.entities.UserRegister;

public class AppRepository {
    UserDao userDao;
    UserAPI api;

    public AppRepository(){

        LocalDB db = LocalDB.getDatabase(LoginActivity.getInstance());
        this.userDao = db.userDao();
        this.api = UserAPI.getInstance();
    }


    public void get(){
        api.get(userDao);
    }


    public void addUser(UserRegister user){
        api.addUser(user);
        User user1 = new User(user.getUserName(), user.getNickName(), user.getPassword(), user.getImage());
        userDao.insert(user1);
    }

    public void sendUser(UserLogin user){
        api.sendUser(user);
    }






}
