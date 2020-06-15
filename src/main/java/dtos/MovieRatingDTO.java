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
public class MovieRatingDTO {
    
    private ImdbDTO imdbDTO;
    private MetacriticDTO mcDTO;
    private RottenTomatoDTO rtDTO;

    public MovieRatingDTO(ImdbDTO imdbDTO, MetacriticDTO mcDTO, RottenTomatoDTO rtDTO) {
        this.imdbDTO = imdbDTO;
        this.mcDTO = mcDTO;
        this.rtDTO = rtDTO;
    }
    
    
    
    
    
}
