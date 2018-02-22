package com.nabenik.demo.dto;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbDTO {
	
	@JsonProperty("Plot")
    String plot;
	@JsonProperty("Ratings")
    List<MovieRatingDTO> ratings;
	
    public OmdbDTO() {
		super();
	}


	public OmdbDTO(String plot, List<MovieRatingDTO> ratings) {
		super();
		this.plot = plot;
		this.ratings = ratings;
	}

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<MovieRatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<MovieRatingDTO> ratings) {
        this.ratings = ratings;
    }


	@Override
	public String toString() {
		return "OmdbDTO [plot=" + plot + ", ratings=" + ratings + "]";
	}

}

class MovieRatingDTO{
	@JsonProperty("Source")
    private String source;
	@JsonProperty("Value")
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	@Override
	public String toString() {
		return "MovieRatingDTO [source=" + source + ", value=" + value + "]";
	}
    
}
