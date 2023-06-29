package domain;

import com.google.gson.annotations.Expose;

public class MessageKind<T> {

    @Expose
    T body;
    @Expose
    MessageAction messageAction;
    @Expose
    int userId;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public MessageAction getMessageAction() {
        return messageAction;
    }

    public void setMessageAction(MessageAction messageAction) {
        this.messageAction = messageAction;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
