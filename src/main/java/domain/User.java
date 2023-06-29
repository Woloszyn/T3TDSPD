package domain;

import com.google.gson.annotations.Expose;
import org.eclipse.jetty.websocket.api.Session;

public class User implements Runnable, Comparable<User> {
    @Expose
    long id;

    @Expose
    String username;

    Session session;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(User o) {
        return o.getId() == this.getId() ? 0 : 1;
    }

    @Override
    public void run() {
        System.out.println("User " + this.getUsername() + " is running");
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
