package com.nabenik.demo.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

@RequestScoped
@Path("/hello")
@Produces("text/plain")
@Consumes("text/plain")
public class HelloEndpoint {
	
	@GET
	public String doHello() {

	    String message =  "Hello from payara - ";
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            message.concat("Your current IP address : " + ip + " - ");
            message.concat("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
	    return message;
	}

    @GET
    @Path("/membomb")
    public String doMemBomb() {
        List<Double> list = new ArrayList<>();

        DoubleStream.generate(() -> Math.random())
                .limit(Integer.MAX_VALUE)
                .forEach(list::add);

        return "Bomb has been deactivated";
    }

}
