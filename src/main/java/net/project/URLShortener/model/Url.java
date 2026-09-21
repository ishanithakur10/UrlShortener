package net.project.URLShortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
public class Url {

    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime creationDate;
    private LocalDateTime expiraionDate;

    public Url(long id, String originalUrl, String shortUrl,LocalDateTime creationDate,LocalDateTime expiraionDate){
        this.id=id;
        this.originalUrl=originalUrl;
        this.shortUrl=shortUrl;
        this.creationDate=creationDate;
        this.expiraionDate=expiraionDate;
    }

    public Url(){
    }

    public Url(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpiraionDate() {
        return expiraionDate;
    }

    public void setExpiraionDate(LocalDateTime expiraionDate) {
        this.expiraionDate = expiraionDate;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", creationDate=" + creationDate +
                ", expiraionDate=" + expiraionDate +
                '}';
    }
}
