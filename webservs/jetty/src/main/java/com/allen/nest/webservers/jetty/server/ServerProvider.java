package com.allen.nest.webservers.jetty.server;

import org.eclipse.jetty.server.Server;

public class ServerProvider {

    public static Server getServer(int port) {
        return new Server(port);
    }

}
