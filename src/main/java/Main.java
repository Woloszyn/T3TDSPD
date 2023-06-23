import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class Main {
    public static void main(String[] args) {
        // Create the Jetty server
        Server server = new Server(8080);

        // Create a ServletContextHandler
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add the WebSocketHandler to the server
        WebSocketHandler handler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(MyWebSocketHandler.class);
            }
        };
        context.setHandler(handler);

        try {
            // Start the server
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop the server
            server.destroy();
        }
    }
}
