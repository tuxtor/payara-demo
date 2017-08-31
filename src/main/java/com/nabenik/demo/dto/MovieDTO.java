package com.nabenik.demo.dto;

public class MovieDTO {
	private String movie;
	private String director;
	
	private String[] keywords;
	private String[] genres;
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
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public String[] getGenres() {
		return genres;
	}
	public void setGenres(String[] genres) {
		this.genres = genres;
	}
	
	
	
}
