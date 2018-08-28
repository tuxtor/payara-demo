package com.nabenik.demo.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Metered;

import com.nabenik.demo.controller.MovieDao;
import com.nabenik.demo.dto.MovieDTO;
import com.nabenik.demo.dto.OmdbDTO;
import com.nabenik.demo.model.Movie;

@RequestScoped
@Path("/movies")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class MovieEndpoint {

	@Inject
	MovieDao movieService;
	
	//Consul, etcd, . . .
	@Inject
	@ConfigProperty(name = "omdbservice", defaultValue = "http://localhost:8080/omdb-demo/rest/omdb/")
	String omdbDaemonServiceUrl;

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(movie).build();
	}
	
	@Metered
	@GET
	@Path("/expanded/{id:[0-9][0-9]*}")
	@Fallback(fallbackMethod = "findById")
	public Response findExpandedById(@PathParam("id") final Long id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		//Fill with details from another service
		String imdbId = movie.getMovieImdbLink()
				.replaceAll("http://www.imdb.com/title/", "")
				.replaceAll("/\\?ref_=fn_tt_tt_1", "");
		
		Client client = ClientBuilder.newClient();
		
		
		
		OmdbDTO expandedDetails = client.target(omdbDaemonServiceUrl)
				.path(imdbId)
				.request(MediaType.APPLICATION_JSON)
				.get(OmdbDTO.class);
		
		//Merging results in new DTO
		MovieDTO movieResult = new MovieDTO(movie.getMovieTitle(), 
				movie.getDirectorName(),
				expandedDetails);
		return Response.ok(movieResult).build();
	}

	@GET
	public List<Movie> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		final List<Movie> movies = movieService.listAll(startPosition, maxResult);
		return movies;
	}

}
