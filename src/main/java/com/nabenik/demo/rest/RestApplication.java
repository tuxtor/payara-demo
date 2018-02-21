package com.nabenik.demo.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

@ApplicationPath("/rest")
public class RestApplication extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(HelloEndpoint.class);
        resources.add(MovieEndpoint.class);
        resources.add(JacksonFeature.class);
        resources.add(JacksonConfig.class);
        return resources;
    }
}
