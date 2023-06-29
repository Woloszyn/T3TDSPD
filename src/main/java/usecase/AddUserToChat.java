package usecase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.*;
import org.eclipse.jetty.websocket.api.Session;

import java.util.List;

public class AddUserToChat {

    public void addUserToChat(long userId, Session session, String username) {
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setSession(session);
        Chat.getInstance().addUser(user);
        sendId(user);
        notifyAllUsersNewUserAdded(user);
        getAllUsersAlreadyConnecteds(user);
    }

    private void sendId(User user) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        AddMessage addMessage = new AddMessage();
        MessageKind<Long> messageKind = new MessageKind<>();
        messageKind.setMessageAction(MessageAction.GET_MY_ID);
        messageKind.setUserId((int) user.getId());
        messageKind.setBody(user.getId());
        addMessage.execute(gson.toJson(messageKind), user, 0);
    }

    private void getAllUsersAlreadyConnecteds(User user) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        AddMessage addMessage = new AddMessage();
        List<User> users = Chat.getInstance().getUsers();
        users.forEach(usr -> {
            if (user.getId() != usr.getId() && usr.getSession().isOpen()) {
                NewUserAddedDTO newUserAddedDTO = new NewUserAddedDTO();
                newUserAddedDTO.setUser(usr);
                MessageKind<NewUserAddedDTO> messageKind = new MessageKind<>();
                messageKind.setMessageAction(MessageAction.NEW_USER_CONNECTED);
                messageKind.setUserId((int) user.getId());
                messageKind.setBody(newUserAddedDTO);
                addMessage.execute(gson.toJson(messageKind), user, 0);
            }
        });
    }

    public void notifyAllUsersNewUserAdded(User user) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        AddMessage addMessage = new AddMessage();
        NewUserAddedDTO newUserAddedDTO = new NewUserAddedDTO();
        newUserAddedDTO.setUser(user);
        MessageKind<NewUserAddedDTO> newUserAddedDTOMessageKind = new MessageKind<>();
        newUserAddedDTOMessageKind.setMessageAction(MessageAction.NEW_USER_CONNECTED);
        newUserAddedDTOMessageKind.setBody(newUserAddedDTO);
        Chat.getInstance().getUsers().forEach((u) -> {
            if (u.getId() != user.getId()) {
                addMessage.execute(gson.toJson(newUserAddedDTOMessageKind), u, 0);
            }
        });
    }

}
