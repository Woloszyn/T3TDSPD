package usecase;

import com.google.gson.Gson;
import domain.*;
import org.eclipse.jetty.websocket.api.Session;

public class MessageClassificator {

    public static void classify(String message, Session session) {
        AddMessage addMessage = new AddMessage();
        GetUser getUser = new GetUser();
        Gson gson = new Gson();
        MessageRequest messageRequest = gson.fromJson(message, MessageRequest.class);
        switch (messageRequest.getAction()) {
            case SEND_MESSAGE:
                sendMessage(session, addMessage, getUser, gson, messageRequest);
            break;
            case CHAT_WITH_USER:
                chatWithUser(session, addMessage, getUser, gson, messageRequest);
            break;
        }
    }

    private static void chatWithUser(Session session, AddMessage addMessage, GetUser getUser, Gson gson, MessageRequest messageRequest) {
        User actualUser = getUser.bySession(session);
        SendOldMessagesFromUser.execute((int) actualUser.getId(), messageRequest.getTo());
    }

    private static void sendMessage(Session session, AddMessage addMessage, GetUser getUser, Gson gson, MessageRequest messageRequest) {
        DirectMessageDTO directMessageDTO = new DirectMessageDTO();
        directMessageDTO.setUserId(messageRequest.getFrom());
        directMessageDTO.setUserIdToReceive(messageRequest.getTo());
        directMessageDTO.setMessage(messageRequest.getMessage());
        MessageKind<DirectMessageDTO> directMessageDTOMessageKind = new MessageKind<>();
        directMessageDTOMessageKind.setMessageAction(MessageAction.NEW_MESSAGE_RECEIVED);
        directMessageDTOMessageKind.setBody(directMessageDTO);
        directMessageDTOMessageKind.setUserId(messageRequest.getFrom());
        addMessage.execute(gson.toJson(directMessageDTOMessageKind), getUser.bySession(session), messageRequest.getFrom());
        directMessageDTOMessageKind.setUserId(messageRequest.getTo());
        addMessage.execute(gson.toJson(directMessageDTOMessageKind), getUser.byId(messageRequest.getTo()), messageRequest.getFrom());
    }

}
