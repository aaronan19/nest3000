package com.allen.nest.webservers.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.List;

public class ServiceProvider {

    public static ResourceConfig getResourceConfig(String...packages) {
        return new ResourceConfig().packages(packages);
    }

    public static ResourceConfig getResourceConfig(List<String> packages) {
        return new ResourceConfig().packages(packages.toArray(new String[]{}));
    }

    public static ServletContainer getServletContainer(ResourceConfig rc) {
        return rc == null ? new ServletContainer() : new ServletContainer(rc);
    }

}
