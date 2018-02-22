package com.nabenik.demo.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@Table(name="movie")
@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m")
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long movieId;

	private Long budget;

	@Column(name="content_rating")
	private String contentRating;

	private String country;

	@Column(name="director_name")
	private String directorName;

	private Integer duration;

	private String genres;

	private Long gross;

	@Column(name="movie_imdb_link")
	private String movieImdbLink;

	@Column(name="movie_title")
	private String movieTitle;

	@Column(name="plot_keywords")
	private String plotKeywords;

	@Column(name="title_year")
	private Integer titleYear;

	public Movie() {
	}

	public Long getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getBudget() {
		return this.budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public String getContentRating() {
		return this.contentRating;
	}

	public void setContentRating(String contentRating) {
		this.contentRating = contentRating;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDirectorName() {
		return this.directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getGenres() {
		return this.genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public Long getGross() {
		return this.gross;
	}

	public void setGross(Long gross) {
		this.gross = gross;
	}

	public String getMovieImdbLink() {
		return this.movieImdbLink;
	}

	public void setMovieImdbLink(String movieImdbLink) {
		this.movieImdbLink = movieImdbLink;
	}

	public String getMovieTitle() {
		return this.movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getPlotKeywords() {
		return this.plotKeywords;
	}

	public void setPlotKeywords(String plotKeywords) {
		this.plotKeywords = plotKeywords;
	}

	public Integer getTitleYear() {
		return this.titleYear;
	}

	public void setTitleYear(Integer titleYear) {
		this.titleYear = titleYear;
	}

}