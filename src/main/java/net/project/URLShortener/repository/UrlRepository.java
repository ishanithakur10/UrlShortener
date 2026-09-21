package net.project.URLShortener.repository;

import net.project.URLShortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {
    public Url fingByShortLink(String shortLink);
}
