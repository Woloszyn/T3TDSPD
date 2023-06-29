package domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chat {
    private static Chat instance;

    public static Chat getInstance() {
        if (instance == null) {
            instance = new Chat();
        }
        return instance;
    }

    public Chat() {
    }

    List<ChatMessage> chatMessages = new CopyOnWriteArrayList<>();
    List<User> users = new CopyOnWriteArrayList<>();

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addMessage(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
