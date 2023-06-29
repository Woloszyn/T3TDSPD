package adapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.kohsuke.randname.RandomNameGenerator;
import usecase.AddUserToChat;
import usecase.MessageClassificator;

public class ChatWebSocketHandler extends WebSocketAdapter {
    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        RandomNameGenerator randomNameGenerator = new RandomNameGenerator();
        AddUserToChat addUserToChat = new AddUserToChat();
        addUserToChat.addUserToChat(session.hashCode(), session, randomNameGenerator.next());
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
    }

    public void onWebSocketText(String message, Session session) {
        MessageClassificator.classify(message, session);
    }
}
