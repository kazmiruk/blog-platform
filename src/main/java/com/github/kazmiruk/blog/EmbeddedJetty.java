package com.github.kazmiruk.blog;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.IOException;

public class EmbeddedJetty {
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        new EmbeddedJetty().startJetty(getPortFromArgs(args));
    }

    private void startJetty(int port) throws Exception {
        Server server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        server.join();
    }

    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }

        return DEFAULT_PORT;
    }

    private static HandlerCollection getServletContextHandler(WebAppContext context) throws IOException {
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(context);

        HandlerCollection handler = new HandlerCollection();

        handler.addHandler(contextHandlerCollection);

        return handler;
    }

    private static WebAppContext getContext() {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase(
            Thread.currentThread().getContextClassLoader().getResource("webapp").toExternalForm());
        return webAppContext;
    }
}
