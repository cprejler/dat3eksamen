/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;

/**
 *
 * @author casper
 */
public class MovieDTO {
    
    private String title;
    private int year;
    private String plot;
    private String directors;
    private String genres;
    private String cast;
    private String poster;
    private double imdbRating;
    private int imdbVotes;
    
   
    
    public MovieDTO(){}
    public MovieDTO(MovieTitleDTO mTitleDTO, MoviePosterDTO mPosterDTO, ImdbDTO imdbDTO){
        this.title = mTitleDTO.getTitle();
        this.year = mTitleDTO.getYear();
        this.plot = mTitleDTO.getPlot();
        this.directors = mTitleDTO.getDirectors();
        this.genres = mTitleDTO.getGenres();
        this.cast = mTitleDTO.getCast();
        this.poster = mPosterDTO.getPoster();
        this.imdbRating = imdbDTO.getImdbRating();
        this.imdbVotes = imdbDTO.getImdbVotes();
        
        
    }
    
    public MovieDTO(MovieTitleDTO mTitleDTO, MoviePosterDTO mPosterDTO){
        this.title = mTitleDTO.getTitle();
        this.year = mTitleDTO.getYear();
        this.plot = mTitleDTO.getPlot();
        this.directors = mTitleDTO.getDirectors();
        this.genres = mTitleDTO.getGenres();
        this.cast = mTitleDTO.getCast();
        this.poster = mPosterDTO.getPoster();
        
        
    }
    
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

 
    
    
    
}
