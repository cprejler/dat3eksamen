/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author casper
 */
public class MetacriticDTO {
    
    private int metacritic;
    
    
    public MetacriticDTO(){}
    public MetacriticDTO(JsonObject jobj){
        this.metacritic = jobj.get("metacritics").getAsJsonObject().get("metacritic").getAsInt();
    }

    public int getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(int metacritic) {
        this.metacritic = metacritic;
    }

    
    
    
    
}
