package usecase;

import domain.Cluster;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.Servlet;


public class StartElection {
    public void execute(Cluster cluster, int nodeId) {
        // Start the Jetty server
        Server server = new Server(8080);

        // Configure the WebSocket handler
        WebSocketHandler webSocketHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
//                factory.register(MyWebsock.class);
            }
        };

        // Create a servlet context handler
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(new ServletHolder((Servlet) webSocketHandler), "/chat");

        // Create a resource handler to serve static files (e.g., HTML, CSS)
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources");

        // Create a handler list and add the handlers
        HandlerList handlers = new HandlerList();
        handlers.addHandler(resourceHandler);
        handlers.addHandler(servletContextHandler);

        server.setHandler(handlers);

        try {
            // Start the server
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
