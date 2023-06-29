import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import usecase.runner.RunSendMessage;
import usecase.runner.RunServerPrompt;

public class Main {
    public static void main(String[] args) {
        // Create the Jetty server
        Server server = new Server(8076);

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
            runSendMessageJob();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Stop the server
            server.destroy();
        }
    }

    private static void sendMessageAsServer() {
        new Thread(){
            @Override
            public void run() {
                RunServerPrompt runServerPrompt = new RunServerPrompt();
                while (true) {
                    runServerPrompt.execute();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private static void runSendMessageJob() {
        // Create a job that sends a message to all users every 5 seconds
        // ...
        new Thread(){
            @Override
            public void run() {
                RunSendMessage runSendMessage = new RunSendMessage();
                while (true) {
                    runSendMessage.execute();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
