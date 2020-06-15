package facades;

import utils.EMF_Creator;
import entities.RenameMe;
import entities.Search;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class SearchFacadeTest {

    private static EntityManagerFactory emf;
    private static SearchFacade facade;

    public SearchFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/3semeksamen_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = SearchFacade.getFacadeExample(emf);
    }

   
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = SearchFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Search.deleteAllRows").executeUpdate();
            String testJson = "{\n"
                    + "    \"title\": \"Die Hard\",\n"
                    + "    \"year\": 1988,\n"
                    + "    \"plot\": \"John McClane, officer of the NYPD, tries to save wife Holly Gennaro and several others, taken hostage by German terrorist Hans Gruber during a Christmas party at the Nakatomi Plaza in Los Angeles.\",\n"
                    + "    \"directors\": \"John McTiernan\",\n"
                    + "    \"genres\": \"Action,Thriller\",\n"
                    + "    \"cast\": \"Bruce Willis,Bonnie Bedelia,Reginald VelJohnson,Paul Gleason\",\n"
                    + "    \"poster\": \"https://m.media-amazon.com/images/M/MV5BZjRlNDUxZjAtOGQ4OC00OTNlLTgxNmQtYTBmMDgwZmNmNjkxXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_SX677_AL_.jpg\",\n"
                    + "    \"imdbRating\": 8.3,\n"
                    + "    \"imdbVotes\": 535036\n"
                    + "}";
            em.persist(new Search("Die Hard", testJson, new java.util.Date()));
            em.persist(new Search("Die Hard",testJson, new java.util.Date()));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetNumberOfSearches(){
        assertEquals(2, facade.getNumberOfSearches("Die Hard"));
    }
    
    @Test
    public void testGetCachedJSON(){
        Search dieHard = facade.getCachedJson("Die Hard");
        assertTrue(dieHard.getTitle().contains("Die Hard"));
        assertFalse(dieHard.getCachedJson().isEmpty());
        assertTrue(dieHard.getCachedJson().contains("Die Hard"));
        
    }    

}
