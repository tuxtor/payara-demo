package com.nabenik.demo.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nabenik.demo.dto.OmdbDTO;

/**
 * Session Bean implementation class OmdbDao
 */
@Stateless
public class OmdbDao {
	
	@Inject
	Logger logger;
	
	final String BASE_OMDB_URL = "http://www.omdbapi.com/?apikey=a3804773&i=";

	/**
	 * Sync petition to omdb
	 * @param imdbId
	 * @return OmdbDTO representation
	 */
	public OmdbDTO getMovieInfo(String imdbId) throws IOException{
		Client client = ClientBuilder.newClient();
		
		logger.log(Level.INFO, "Retrieving " + BASE_OMDB_URL.concat(imdbId));
		
		String details =  client.target(BASE_OMDB_URL.concat(imdbId))
				.request(MediaType.APPLICATION_JSON)
				.get(String.class);
		logger.log(Level.INFO, details.toString());
		
		//Dummy marshalling
		ObjectMapper mapper = new ObjectMapper();
		OmdbDTO omdbDTO = new OmdbDTO();
		omdbDTO = mapper.readValue(details, OmdbDTO.class);
		return omdbDTO;
		
	}

}
