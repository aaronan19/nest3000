package com.allen.nest.webservers.jetty;

import com.allen.nest.webservers.jetty.server.ServerBuilder;
import org.eclipse.jetty.server.Server;
import org.junit.Test;

public class JettyServerBuilderTest {

    @Test
    public void createJettyServer() {
        ServerBuilder builder = new ServerBuilder();
        Server server = builder.contextPath("/myserver/*").port(2222).buildServer();
        new Thread(() -> {
            try {
                server.start();
                server.join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();
    }
}
