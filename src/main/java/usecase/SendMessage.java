package usecase;

import domain.Chat;
import domain.ChatMessage;
import domain.User;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

public class SendMessage {

    private static Semaphore semaphore = new Semaphore(1);

    public static void execute() {
        GetUser getUser = new GetUser();
        List<ChatMessage> chatMessages = Chat.getInstance().getChatMessages().stream().filter(chatMessage -> !chatMessage.isSended()).collect(Collectors.toList());
        for (ChatMessage chatMessage : chatMessages) {
            User user = getUser.byId((int) chatMessage.getUser().getId());
            try {
                semaphore.acquire();
                if (user.getSession().isOpen()) {
                    user.getSession().getRemote().sendString(chatMessage.getMessage());
                }
                chatMessage.setSended(true);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

}
