/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonObject;

/**
 *
 * @author casper
 */
public class MoviePosterDTO {
    private String poster;
    
    
    public MoviePosterDTO(){}
    public MoviePosterDTO(JsonObject jobj) {
        this.poster = jobj.get("poster").getAsString();
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
    
    
    
    
}
