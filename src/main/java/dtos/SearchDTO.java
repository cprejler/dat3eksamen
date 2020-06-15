/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonObject;
import entities.Search;
import java.util.Date;

/**
 *
 * @author casper
 */
public class SearchDTO {
    
    private String title;
    private String movie;
    private Date cachedDate;

    public SearchDTO(Search search) {
        this.title = search.getTitle();
        this.movie = search.getCachedJson();
        this.cachedDate = search.getDate();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Date getCachedDate() {
        return cachedDate;
    }

    public void setCachedDate(Date cachedDate) {
        this.cachedDate = cachedDate;
    }
    
    
    
    
    
    
}
