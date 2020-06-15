package facades;

import entities.RenameMe;
import entities.Search;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class SearchFacade {

    private static SearchFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private SearchFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static SearchFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SearchFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public void addSearch(Search search){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(search);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
    
    public long getNumberOfSearches(String param){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Long> searchCount = em.createQuery("SELECT COUNT(s) FROM Search s WHERE s.title like :param ", Long.class);
            searchCount.setParameter("param", param);
            
            return searchCount.getSingleResult();
        }finally{  
            em.close();
        }
        
    }
    
    public Search getCachedJson(String param){
        EntityManager em = getEntityManager();
        
        try{
            TypedQuery<Search> cachedJson = em.createQuery("SELECT s FROM Search s WHERE s.title like :param ", Search.class);
            cachedJson.setParameter("param", param);
            List<Search> searches =  cachedJson.getResultList();
            return searches.get(0);
            
        }finally{
            em.close();
        }
    }

}
