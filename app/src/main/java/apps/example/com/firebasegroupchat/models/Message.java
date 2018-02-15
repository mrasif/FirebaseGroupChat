package apps.example.com.firebasegroupchat.models;

/**
 * Created by root on 14/2/18.
 */

public class Message {
    String message;
    String name;

    public Message() {
    }

    public Message(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
