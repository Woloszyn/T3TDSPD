package usecase;

import domain.Chat;
import domain.User;
import org.eclipse.jetty.websocket.api.Session;

import java.util.Optional;

public class GetUser {

    public User bySession(Session session) {
        Optional<User> userOptional = Chat.getInstance().getUsers().stream().filter(user -> user.getId() == session.hashCode()).findFirst();
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        return userOptional.get();
    }
    public User byId(int id) {
        Optional<User> userOptional = Chat.getInstance().getUsers().stream().filter(user -> user.getId() == id).findFirst();
        if (userOptional.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        return userOptional.get();
    }

}
