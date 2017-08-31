package com.nabenik.demo.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Path("/hello")
@Produces("text/plain")
@Consumes("text/plain")
public class HelloEndpoint {
	
	@GET
	public String doHello() {
		return "Hello from payara";
	}

}
