package domain;

import com.google.gson.annotations.Expose;

public class DirectMessageDTO {

    @Expose
    private int userId;
    @Expose
    private int userIdToReceive;
    @Expose
    private String message;
    @Expose
    private String createdAt;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserIdToReceive() {
        return userIdToReceive;
    }

    public void setUserIdToReceive(int userIdToReceive) {
        this.userIdToReceive = userIdToReceive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
