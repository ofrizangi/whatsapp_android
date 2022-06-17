package com.example.whatsappandriodclient.repositories;

import com.example.whatsappandriodclient.LocalDB;
import com.example.whatsappandriodclient.LoginActivity;
import com.example.whatsappandriodclient.R;
import com.example.whatsappandriodclient.api.UserAPI;
import com.example.whatsappandriodclient.dao.AppDao;
import com.example.whatsappandriodclient.dao.UserDao;
import com.example.whatsappandriodclient.entities.App;
import com.example.whatsappandriodclient.entities.User;
import com.example.whatsappandriodclient.objectAPI.UserLogin;
import com.example.whatsappandriodclient.objectAPI.UserRegister;

import java.util.List;

public class AppRepository {
    UserDao userDao;
    AppDao appDao;
    UserAPI api;

    public AppRepository(){

        LocalDB db = LocalDB.getDatabase(LoginActivity.getInstance());
        this.appDao = db.appDao();
        String mystring = LoginActivity.getInstance().getResources().getString(R.string.BaseUrl);
        String myInvitaionstring = LoginActivity.getInstance().getResources().getString(R.string.url);
        insertServer(new App(mystring), new App(myInvitaionstring));
        this.api = UserAPI.getInstance();
        this.userDao = db.userDao();

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


    public void insertServer(App server, App server2){
        List<App> list = appDao.index();
        if(list.isEmpty()){
            appDao.insert(server);
            appDao.insert(server2);
        }

    }

    public void deleteServer(App myServer){
        appDao.delete(myServer);
    }

    public List<App> getServer(){
        return appDao.index();
    }



}
