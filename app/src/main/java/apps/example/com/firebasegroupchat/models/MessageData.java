package apps.example.com.firebasegroupchat.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 14/2/18.
 */

public class MessageData {
    List<Message> messages;

    public MessageData() {
        this.messages=new ArrayList<>();
    }

    public MessageData(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public Message getMessage(int position){
        return messages.get(position);
    }
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int size(){
        return messages.size();
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "messages=" + messages +
                '}';
    }
}
