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
public class ImdbDTO {
    
    private double imdbRating;
    private int imdbVotes;
    
   
    public ImdbDTO(JsonObject jobj){
        this.imdbRating = jobj.getAsJsonObject("imdb").get("imdbRating").getAsDouble();
        this.imdbVotes = jobj.getAsJsonObject("imdb").get("imdbVotes").getAsInt();
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
