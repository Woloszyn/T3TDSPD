package domain;

public class MessageRequest {

    MessageRequestActions action;
    int from;
    int to;
    String message;

    public MessageRequestActions getAction() {
        return action;
    }

    public void setAction(MessageRequestActions action) {
        this.action = action;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
