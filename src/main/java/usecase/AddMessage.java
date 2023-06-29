package usecase;

import domain.Chat;
import domain.ChatMessage;
import domain.User;

public class AddMessage {

    public void execute(String message, User user, int fromUserId) {
        var chatMessage = new ChatMessage(user, message);
        chatMessage.setFromUserId(fromUserId);
        Chat.getInstance().addMessage(chatMessage);
    }

}
