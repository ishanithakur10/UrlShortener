package net.project.URLShortener.service;

import com.google.common.hash.Hashing;
import net.project.URLShortener.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import net.project.URLShortener.model.Url;
import net.project.URLShortener.model.UrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {
        if (StringUtils.isNotEmpty(urlDto.getUrl())) {
            String encodedUrl = encodeUrl(urlDto.getUrl());
            Url urlToPersist = new Url();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setOriginalUrl(urlDto.getUrl());
            urlToPersist.setShortUrl(encodedUrl);
            urlToPersist.setExpiraionDate(getExpiraionDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
            Url UrlToReturn=persistShortLink(urlToPersist);

            if(UrlToReturn !=null){
                return UrlToReturn;
            }
            return  null;
        }
        return null;
    }

    private LocalDateTime getExpiraionDate(String expirationDate, LocalDateTime creationDate) {

        if(StringUtils.isBlank(expirationDate)){
            return creationDate.plusSeconds(60);
        }
        LocalDateTime expirationTimeToReturn= LocalDateTime.parse(expirationDate);
        return expirationTimeToReturn;
    }

    private String encodeUrl(String url){
        String encodedUrl="";
        LocalDateTime time=LocalDateTime.now();
        encodedUrl= Hashing.murmur3_32().hashString(
                url.concat(time.toString()), StandardCharsets.UTF_8).toString();

        return encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToReturn = urlRepository.save(url);
        return urlToReturn;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToReturn=urlRepository.fingByShortLink(url);
        return urlToReturn;
    }

    @Override
    public void deleteShortLink(Url url) {
        urlRepository.delete(url);
    }
}
