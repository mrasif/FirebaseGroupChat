package apps.example.com.firebasegroupchat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import apps.example.com.firebasegroupchat.R;
import apps.example.com.firebasegroupchat.models.Message;
import apps.example.com.firebasegroupchat.models.MessageData;

/**
 * Created by root on 15/2/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    MessageData messages;

    public MessageAdapter(Context context, MessageData messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message=messages.getMessage(position);
        holder.tvTitle.setText(message.getName());
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvMessage=itemView.findViewById(R.id.tvMessage);

        }
    }
}
