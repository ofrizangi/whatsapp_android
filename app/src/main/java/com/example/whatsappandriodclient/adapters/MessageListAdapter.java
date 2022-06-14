package com.example.whatsappandriodclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappandriodclient.R;
import com.example.whatsappandriodclient.entities.Message;

import java.util.List;


public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private final LayoutInflater minflater;
    private List<Message> messages;

    private static final int LAYOUT_ONE = 0;
    private static final int LAYOUT_TWO = 1;

    class MessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView message;

        private MessageViewHolder(View itemView){
            super(itemView);

            this.message = itemView.findViewById(R.id.message);

        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if(messages.get(position).isSent())
            return LAYOUT_ONE;
        else
            return LAYOUT_TWO;
    }

    public MessageListAdapter(Context context){
        minflater = LayoutInflater.from(context);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        MessageViewHolder viewHolder = null;

        if(viewType==LAYOUT_ONE)
        {
            View itemView = minflater.inflate(R.layout.send_message, parent, false);
            viewHolder = new MessageViewHolder(itemView);
        }
        else
        {
            View itemView = minflater.inflate(R.layout.recieve_message, parent, false);
            viewHolder= new MessageViewHolder(itemView);
        }

        return viewHolder;
//        View itemView = minflater.inflate(R.layout.message, parent, false);
//        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder messageViewHolder, int position){
        if(messages != null){
            final Message current = messages.get(position);
            messageViewHolder.message.setText(current.getContent());
        }
    }

    public void setMessages(List<Message> m){
        messages = m;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount(){
        if(messages != null)
            return messages.size();
        else
            return 0;
    }

    public List<Message> getMessages(){
        return messages;
    }


}