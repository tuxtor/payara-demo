package com.nabenik.demo.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nabenik.demo.controller.MovieDao;
import com.nabenik.demo.controller.OmdbDao;
import com.nabenik.demo.dto.MovieDTO;
import com.nabenik.demo.dto.OmdbDTO;
import com.nabenik.demo.model.Movie;
import com.nabenik.demo.util.WordChopper;

@RequestScoped
@Path("/movies")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class MovieEndpoint {

	@Inject
	MovieDao movieService;
	
	@Inject
	WordChopper wordChopper;
	
	@Inject
	OmdbDao omdbService;
	
	@Inject
	Logger logger;
	
	@POST
	public Response create(final Movie movie) {
		movieService.create(movie);
		return Response.ok().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
		return Response.ok(movie).build();
	}
	
	@GET
	@Path("/expanded/{id:[0-9][0-9]*}")
	public Response findExpandedById(@PathParam("id") final Long id) {
		Movie movie = movieService.findById(id);
		if (movie == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		//Fill with details from another service
		String imdbId = movie.getMovieImdbLink()
				.replaceAll("http://www.imdb.com/title/", "")
				.replaceAll("/\\?ref_=fn_tt_tt_1", "");
		logger.log(Level.INFO, "Retrieving movie from omdb title " + imdbId);
		
		OmdbDTO expandedDetails = omdbService.getMovieInfo(imdbId);
		
		//Merging results in new DTO
		MovieDTO movieResult = new MovieDTO(movie.getMovieTitle(), 
				movie.getDirectorName(), 
				wordChopper.chopString(movie.getPlotKeywords()), 
				wordChopper.chopString(movie.getGenres()),
				expandedDetails);
		return Response.ok(movieResult).build();
	}

	@GET
	public List<Movie> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		final List<Movie> movies = movieService.listAll(startPosition, maxResult);
		return movies;
	}
	
	@GET
	@Path("/mobile")
	public List<MovieDTO> listMobile(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		final List<Movie> movies = movieService.listAll(startPosition, maxResult);
		
		return movies.stream()
				.map(x -> new MovieDTO(x.getMovieTitle(), 
							x.getDirectorName(), 
							wordChopper.chopString(x.getPlotKeywords()), 
							wordChopper.chopString(x.getGenres()))
				)
				.collect(Collectors.toList());
		
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Movie movie) {
		Movie updatedEntity = movieService.update(movie);
        if (updatedEntity == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		movieService.deleteById(id);
        return Response.ok().build();
	}

}
