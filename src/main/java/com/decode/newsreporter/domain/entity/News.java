package com.decode.newsreporter.domain.entity;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import lombok.Data;
import java.util.Date;

@Data
public class News {
    private Long id;
    private final Date date;
    private final URL URL;
    private final NewsName newsName;
    private final String body;

    public News(Long id, URL URL, NewsName newsName, String body) throws IllegalArgumentException {
        this.id = id;
        this.date = new Date();
        this.URL = URL;
        this.newsName = newsName;
        this.body = body;
    }
}
