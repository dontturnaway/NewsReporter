package com.decode.newsreporter.Domain.Entity;
import com.decode.newsreporter.Domain.ValueObject.URL;

import java.util.Date;

public class News {
    private Long id;
    private final Date date;
    private final URL URL;
    private final String name;
    private final String body;

    public News(Long id, URL URL, String name, String body) throws IllegalArgumentException {
        this.id = id;
        this.date = new Date();
        this.URL = URL;
        this.name = name;
        this.body = body;
    }

    public News(String name, URL URL, String body) throws IllegalArgumentException {
        this.date = new Date();
        this.URL = URL;
        this.name = name;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public URL getURL() {
        return URL;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }
}
