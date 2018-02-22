package com.nabenik.demo.dto;

public class MovieDTO {
	private String movie;
	private String director;
	
	private OmdbDTO details;
	
	public MovieDTO(String movie, String director) {
		super();
		this.movie = movie;
		this.director = director;
	}
	
	public MovieDTO(String movie, String director, OmdbDTO details) {
		this(movie, director);
		this.details = details;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public OmdbDTO getDetails() {
		return details;
	}

	public void setDetails(OmdbDTO details) {
		this.details = details;
	}

	
	
	
}
