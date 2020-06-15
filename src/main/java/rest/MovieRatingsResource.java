package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.ImdbDTO;
import dtos.MetacriticDTO;
import dtos.MovieRatingDTO;
import dtos.RottenTomatoDTO;
import utils.EMF_Creator;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

@RolesAllowed("user")
@Path("movie-info-all-ratings")
public class MovieRatingsResource {

    Gson gson = new Gson();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/3semeksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final FacadeExample FACADE = FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefault() throws IOException {

        return GSON.toJson("It's live");

    }

    @Path("title/{q}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRatingByTitle(@PathParam("q") String q) throws IOException, InterruptedException, ExecutionException {

        String query = q.replace(" ", "%20");

        
        Future<JsonObject> imdbFuture = getRating(query, "i");
        Future<JsonObject> rtFuture = getRating(query, "t");
        Future<JsonObject> metaFuture = getRating(query, "m");
       
        JsonObject imdb = imdbFuture.get();
        JsonObject tomato = rtFuture.get();
        JsonObject metacritic = metaFuture.get();
        System.out.println("TOMATO" + tomato);
        ImdbDTO imdbDTO = new ImdbDTO(imdb);
        RottenTomatoDTO rtDTO = new RottenTomatoDTO(tomato);
        MetacriticDTO mcDTO = new MetacriticDTO(metacritic);
        
        MovieRatingDTO mrDTO = new MovieRatingDTO(imdbDTO, mcDTO, rtDTO);

       
        return gson.toJson(mrDTO);

    }

    

    private static Future<JsonObject> getRating(String query, String param) {
        return executor.submit(() -> {
            String data = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + query + "/"+param);
            JsonObject json = GSON.fromJson(data, JsonObject.class);
            
            
            return json;
        });
        
        
    }


}
