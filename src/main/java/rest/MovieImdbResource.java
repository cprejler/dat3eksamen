package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dtos.ImdbDTO;
import dtos.MovieDTO;
import dtos.MoviePosterDTO;
import dtos.MovieTitleDTO;
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

//Todo Remove or change relevant parts before ACTUAL use
@RolesAllowed({"user", "admin"})
@Path("movie-info-imdb")
public class MovieImdbResource {

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
    public String getMovieByTitle(@PathParam("q") String q) throws IOException, InterruptedException, ExecutionException {

        String query = q.replace(" ", "%20");

        Future<JsonObject> titlePropertiesFuture = getTitleProperties(query);
        Future<JsonObject> moviePosterFuture = getMoviePoster(query);
        Future<JsonObject> imdbFuture = getIMDBRating(query);

        JsonObject titleProperties = titlePropertiesFuture.get();
        JsonObject moviePoster = moviePosterFuture.get();
        JsonObject imdb = imdbFuture.get();
        

        MovieTitleDTO mTitleDTO = new MovieTitleDTO(titleProperties);
        MoviePosterDTO mPosterDTO = new MoviePosterDTO(moviePoster);
        ImdbDTO imdbDTO = new ImdbDTO(imdb);
        MovieDTO mDTO = new MovieDTO(mTitleDTO, mPosterDTO, imdbDTO);
        return gson.toJson(mDTO);

    }

    private static Future<JsonObject> getTitleProperties(String parameter) throws IOException {

        return executor.submit(() -> {
            String data = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/movieInfo/" + parameter);
            JsonObject json = GSON.fromJson(data, JsonObject.class);
            return json;
        });

    }

    private static Future<JsonObject> getMoviePoster(String parameter) throws IOException {

        return executor.submit(() -> {
            String data = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/moviePoster/" + parameter);
            JsonObject json = GSON.fromJson(data, JsonObject.class);
            return json;
        });

    }

    private static Future<JsonObject> getIMDBRating(String parameter) {
        return executor.submit(() -> {
            String data = HttpUtils.fetchData("https://ex2-0-2-0.mydemos.dk/ratings/" + parameter + "/i");
            JsonObject json = GSON.fromJson(data, JsonObject.class);
            
            System.out.println(json);
            return json;
        });
        
        
    }

}
