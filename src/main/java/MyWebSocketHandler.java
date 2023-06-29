import adapter.ChatWebSocketHandler;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class MyWebSocketHandler {
    ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();

    @OnWebSocketConnect
    public void onConnect(Session session) {
        chatWebSocketHandler.onWebSocketConnect(session);
        System.out.println("WebSocket connected: " + session.getRemoteAddress().toString());
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        chatWebSocketHandler.onWebSocketClose(statusCode, reason);
        System.out.println("WebSocket closed: " + reason);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        chatWebSocketHandler.onWebSocketText(message, session);
        System.out.println("Received message from WebSocket: " + message);
    }

    @OnWebSocketError
    public void onError(Session session, Throwable error) {
        chatWebSocketHandler.onWebSocketError(error);
        System.err.println("WebSocket error:");
        error.printStackTrace();
    }
}
