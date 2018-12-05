package com.allen.nest.webservers.jetty.servlet;

import org.eclipse.jetty.servlet.FilterMapping;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class FilterMappingBuilder {
    private String filterName = null;
    private String[] pathSpecs = null;
    private String[] servletNames = null;
    private EnumSet dispatcherTypes = null;

    public FilterMappingBuilder() {

    }

    public FilterMappingBuilder filterName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    public FilterMappingBuilder pathSpecs(String[] pathSpecs) {
        this.pathSpecs = pathSpecs;
        return this;
    }

    public FilterMappingBuilder servletNames(String[] servletNames) {
        this.servletNames = servletNames;
        return this;
    }

    public FilterMappingBuilder dispatcherTypes(EnumSet dispatcherTypes) {
        this.dispatcherTypes = dispatcherTypes;
        return this;
    }

    public FilterMapping buildFilterMapping() {
        if(filterName == null || pathSpecs == null || servletNames == null || dispatcherTypes == null) {
            //TODO
        }
        FilterMapping filterMapping = new FilterMapping();
        filterMapping.setFilterName(filterName);
        filterMapping.setPathSpecs(pathSpecs);
        filterMapping.setServletNames(servletNames);
        filterMapping.setDispatcherTypes(dispatcherTypes);
        return filterMapping;
    }
}
