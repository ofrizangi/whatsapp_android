package com.example.whatsappandriodclient.api;

import com.example.whatsappandriodclient.dao.ContactDao;
import com.example.whatsappandriodclient.objectAPI.ContactToAdd;
import com.example.whatsappandriodclient.objectAPI.Transfer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TransferAPI {

    Retrofit retrofit;
    WebServiceAPI webServiceAPI;


    public TransferAPI(String contactServer) {
        String port = contactServer.substring(9);
        final String url = "http://10.0.2.2" + port + "/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }


    public WebServiceAPI getMyApi() {
        return webServiceAPI;
    }

    public void transferMessage(Transfer transfer, String token, ContactToAdd contact, String userID, ContactDao contactDao) {
//        Call<Void> call = webServiceAPI.transferMessage()
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                if(response.isSuccessful()) {
//                    Contact contact1 = new Contact(contact.getContactName(), contact.getContactNickName(), contact.getServer(), userID);
//                    contactDao.insert(contact1);
//                    Intent myIntent = new Intent(AddContactActivity.getInstance(), ChatListActivity.class);
////                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    myIntent.putExtra("token", token);
//                    myIntent.putExtra("userName", userID);
//                    AddContactActivity.getInstance().startActivity(myIntent);
//                }
//                else{
//                    Log.i("delete", "delete");
//                    ContactAPI.getInstance().deleteContact( token, contact.getContactName());
//                    AlertDialog.Builder alert = new AlertDialog.Builder(AddContactActivity.getInstance());
//                    alert.setTitle("error");
//                    alert.setMessage("could not invite this contact - make sure the user name exists in " +
//                            "the server or the server is correct");
//                    alert.create().show();
//                }
//            }
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.i("in fail", "fail");
//            }
//        });

    }

}






