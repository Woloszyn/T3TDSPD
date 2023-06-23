package adapter;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends WebSocketAdapter {
    private static Set<Session> sessions = new HashSet<>();

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        sessions.add(session);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        sessions.remove(getSession());
    }

    @Override
    public void onWebSocketText(String message) {
        super.onWebSocketText(message);
        broadcast(message);
    }

    private void broadcast(String message) {
        sessions.forEach(session -> {
            try {
                session.getRemote().sendString(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
