package apps.example.com.firebasegroupchat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import apps.example.com.firebasegroupchat.R;
import apps.example.com.firebasegroupchat.models.Message;
import apps.example.com.firebasegroupchat.utils.AllMethods;

/**
 * Created by root on 15/2/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    List<Message> messages;
    DatabaseReference messagedb;

    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messagedb) {
        this.context = context;
        this.messages = messages;
        this.messagedb=messagedb;
    }

    public void addMessage(Message message){
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=messages.get(position);
        if(message.getName().equals(AllMethods.name)){
            holder.tvTitle.setText("You");
            holder.tvTitle.setGravity(Gravity.RIGHT);
            holder.llMessage.setBackgroundColor(Color.parseColor("#EF9E73"));
            holder.ibDelete.setBackgroundColor(Color.parseColor("#EF9E73"));
        }
        else {
            holder.tvTitle.setText(message.getName());
            holder.ibDelete.setVisibility(View.GONE);
        }
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvMessage;
        ImageButton ibDelete;
        LinearLayout llMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvMessage=itemView.findViewById(R.id.tvMessage);
            ibDelete=itemView.findViewById(R.id.ibDelete);
            llMessage=itemView.findViewById(R.id.llMessage);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messagedb.child(messages.get(getPosition()).getKey()).removeValue();
                }
            });

        }
    }
}
