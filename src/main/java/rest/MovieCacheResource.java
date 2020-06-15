package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.ImdbDTO;
import dtos.MovieDTO;
import dtos.MoviePosterDTO;
import dtos.MovieTitleDTO;
import dtos.SearchDTO;
import entities.Search;
import utils.EMF_Creator;
import facades.FacadeExample;
import facades.SearchFacade;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.HttpUtils;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie-info-all-v2")
public class MovieCacheResource {

    Gson gson = new Gson();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/3semeksamen",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final SearchFacade FACADE = SearchFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDefault() throws IOException {

        return GSON.toJson("It's live");

    }

    @Path("title/{q}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByTitle(@PathParam("q") String q) throws IOException, InterruptedException, ExecutionException {

        Search cachedData = FACADE.getCachedJson(q);
        String json = cachedData.getCachedJson();
        JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
        return GSON.toJson(convertedObject);

    }

    
    

}
