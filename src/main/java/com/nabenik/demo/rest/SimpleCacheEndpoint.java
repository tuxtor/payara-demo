package com.nabenik.demo.rest;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@RequestScoped
@Path("/cache")
@Produces("application/json")
@Consumes("application/json")
public class SimpleCacheEndpoint {

    @GET
    @CacheResult(cacheName = "rest-nabcache")
    public String getJSON(@QueryParam("key") @CacheKey String key ) {
        return "hola";
    }

    @PUT
    @CachePut(cacheName = "rest-nabcache") 
    public void putJSON(@QueryParam("key") @CacheKey String key, @CacheValue String content ) {
    }


    @DELETE
    @CacheRemove(cacheName = "rest-nabcache")
    public void deleteJSON(@QueryParam("key") @CacheKey String key){ }
}
