package com.allen.nest.webservers.jetty.servlet;

import org.eclipse.jetty.servlet.FilterHolder;

import javax.servlet.Filter;

public class FilterHolderBuilder {
    private String name;
    private Class<? extends Filter> filterClass;

    public FilterHolderBuilder() {

    }

    public FilterHolderBuilder filterName(String name) {
        this.name = name;
        return this;
    }

    public FilterHolderBuilder filterClass(Class<? extends Filter> filterClass) {
        this.filterClass = filterClass;
        return this;
    }

    public FilterHolder buildFilterHolder() {
        if(name == null || filterClass == null) {
            //TODO
        }
        FilterHolder filterHolder = new FilterHolder(filterClass);
        filterHolder.setName(name);
        return filterHolder;
    }
}
