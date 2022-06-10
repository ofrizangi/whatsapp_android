package com.example.whatsappandriodclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappandriodclient.R;
import com.example.whatsappandriodclient.entities.Contact;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final TextView contactNickName;

        private ContactViewHolder(View itemView){
            super(itemView);
            this.contactNickName = itemView.findViewById(R.id.contactname);
        }
    }

    private final LayoutInflater minflater;
    private List<Contact> contacts;

    public ContactListAdapter(Context context){
        minflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = minflater.inflate(R.layout.contact_in_list, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int position){
        if(contacts != null){
            final Contact current = contacts.get(position);
            contactViewHolder.contactNickName.setText(current.getContactNickName());
        }
    }

    public void setContacts(List<Contact> c){
        contacts = c;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){
        if(contacts != null)
            return contacts.size();
        else
            return 0;
    }

    public List<Contact> getContacts(){
        return contacts;
    }








}
