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
public class RottenTomatoDTO {
    

    private double viewerRating;
    private int numViewerReviews;
    private int viewerMeter;
    private double criticRating;
    private int numCriticReviews;
    private int criticMeter;
    
    public RottenTomatoDTO(JsonObject jobj){
        this.viewerRating = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("viewer").get("rating").getAsDouble();
        this.numViewerReviews = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("viewer").get("numReviews").getAsInt();
        this.viewerMeter = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("viewer").get("meter").getAsInt();
        this.criticRating = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("critic").get("rating").getAsDouble();
        this.numCriticReviews = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("critic").get("numReviews").getAsInt();
        this.criticMeter = jobj.get("tomatoes").getAsJsonObject().getAsJsonObject("critic").get("meter").getAsInt();
        
    }

    public double getViewerRating() {
        return viewerRating;
    }

    public void setViewerRating(double viewerRating) {
        this.viewerRating = viewerRating;
    }

    public int getNumViewerReviews() {
        return numViewerReviews;
    }

    public void setNumViewerReviews(int numViewerReviews) {
        this.numViewerReviews = numViewerReviews;
    }

    public int getViewerMeter() {
        return viewerMeter;
    }

    public void setViewerMeter(int viewerMeter) {
        this.viewerMeter = viewerMeter;
    }

    public double getCriticRating() {
        return criticRating;
    }

    public void setCriticRating(double criticRating) {
        this.criticRating = criticRating;
    }

    public int getNumCriticReviews() {
        return numCriticReviews;
    }

    public void setNumCriticReviews(int numCriticReviews) {
        this.numCriticReviews = numCriticReviews;
    }

    public int getCriticMeter() {
        return criticMeter;
    }

    public void setCriticMeter(int criticMeter) {
        this.criticMeter = criticMeter;
    }

   
    
    
}
