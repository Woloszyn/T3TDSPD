package domain;

public class ChatMessage {

    User user;
    String message;
    boolean sended;
    int fromUserId;

    public ChatMessage(User user, String message) {
        this.message = message;
        this.user = user;
        this.sended = false;
    }

    public ChatMessage() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }
}
