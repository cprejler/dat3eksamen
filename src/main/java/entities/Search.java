package entities;

import dtos.MovieDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="search_history")
@NamedQuery(name = "Search.deleteAllRows", query = "DELETE from Search")
public class Search implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(columnDefinition="LONGTEXT")
    private String cachedJson;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    public Search() {
    }

    public Search(String title, String cachedJson, Date date) {
        this.title = title;
        this.cachedJson = cachedJson;
        this.date = date;
    }

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCachedJson() {
        return cachedJson;
    }

    public void setCachedJson(String cachedJson) {
        this.cachedJson = cachedJson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
    
    

   
}
