package com.example.whatsappandriodclient.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsappandriodclient.entities.Contact;
import com.example.whatsappandriodclient.repositories.UserRepository;

import java.util.List;


//The view model wont changed the data - this is the job of the repository
public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    private LiveData<List<Contact>> contactList;

    private String userName;


    public UserViewModel(String userName){
        this.userName = userName;
        this.userRepository = new UserRepository(userName);
        this.contactList = userRepository.getAll();
    }

    public LiveData<List<Contact>> get(){
        return contactList;
    }

    public Contact getAContact(int position){
        Contact c =contactList.getValue().get(position);
//          List<Contact> c0 =contactList.getValue();
//          Contact c = c0.get(position);
//        @Query("SELECT * FROM contact LIMIT  :position")
//        LiveData<List<Contact>> getStudents(int position);

        // Query
//    getStudents(1); // returns the first row of the list

//        Log.i("s","s");
        return c;
    }
    public void setContactView(){
        this.userRepository.setContactView();
    }

    public void getAllContacts(String token){
         this.userRepository.getAllContacts(token);

    }

//    public int getSpecificContactId(int position)


}
