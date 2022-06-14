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


    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView contactNickName;
        private OnContactListener onContactListener;
//        private final ImageView image;

        private ContactViewHolder(View itemView, OnContactListener onMessageListener){
            super(itemView);
            this.contactNickName = itemView.findViewById(R.id.contactname);
//            this.image = itemView.findViewById(R.id.imageView);
//            this.image.setClipToOutline(true);
            this.onContactListener = onMessageListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onContactListener.onContactClick(getAdapterPosition());
        }
    }

    private final LayoutInflater minflater;
    private List<Contact> contacts;
    private OnContactListener onContactListener;

    public ContactListAdapter(Context context, OnContactListener onContactListener){
        minflater = LayoutInflater.from(context);
        this.onContactListener = onContactListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View itemView = minflater.inflate(R.layout.contact_in_list, parent, false);
        return new ContactViewHolder(itemView, this.onContactListener);
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


    public interface OnContactListener {
        void onContactClick(int position);
    }
}
