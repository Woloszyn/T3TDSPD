package domain;

import com.google.gson.annotations.Expose;

public class NewUserAddedDTO {

    @Expose
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
