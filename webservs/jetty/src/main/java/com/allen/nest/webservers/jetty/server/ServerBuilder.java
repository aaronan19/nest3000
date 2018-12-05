package com.allen.nest.webservers.jetty.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerBuilder {

    private ResourceConfig resourceConfig = null;
    private FilterMapping filterMapping = null;
    private FilterHolder filterHolder = null;
    private String servletPath = null;
    private String contextPath = null;
    private int port = -1;

    public ServerBuilder() {

    }

    public ServerBuilder resourceConfig(ResourceConfig resourceConfig) {
        this.resourceConfig = resourceConfig;
        return this;
    }

    public ServerBuilder filterMapping(FilterMapping filterMapping) {
        this.filterMapping = filterMapping;
        return this;
    }

    public ServerBuilder filterHolder(FilterHolder filterHolder) {
        this.filterHolder = filterHolder;
        return this;
    }

    public ServerBuilder servletPath(String servletPath) {
        this.servletPath = servletPath;
        return this;
    }

    public ServerBuilder contextPath(String contextPath) {
        this.contextPath = contextPath;
        return this;
    }

    public ServerBuilder port(int port) {
        this.port = port;
        return this;
    }

    public Server buildServer() {
        Server server = null;
        if(port < 0) {
            server = new Server();
        } else if(port == 0 || port > 65535) {
            //TODO
        } else {
            server = new Server(port);
        }
        configureServer(server);
        return server;
    }

    private void configureServer(Server server) {
        if(resourceConfig != null) {
            ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
            ServletContextHandler context = new ServletContextHandler(server, contextPath);
            context.addServlet(servletHolder, servletPath);
            if(filterHolder != null) {
                context.getServletHandler().addFilter(filterHolder, filterMapping);
            }
        }
    }

}
