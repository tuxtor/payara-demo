package com.nabenik.demo.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;

@Path("/cinemas")
public class CinemaEndpoint {

	
	@Inject
	MetricRegistry registry;
	
	@POST
	@Path("/add/{attendees}")
	public Response addAttendees(@PathParam("attendees") Long attendees) {
	   Metadata metadata = new Metadata("matrix attendees", MetricType.HISTOGRAM);
	   Histogram histogram = registry.histogram(metadata);
	   histogram.update(attendees);
	   return Response.ok().build();
	}
}
