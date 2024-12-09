package com.decode.newsreporter.Domain.Entity;

import java.util.Date;

public class News {
    private Long id;
    private Date date;
    private String URL;
    private String name;
    private String body;

    public News(Long id, String URL, String name, String body) throws IllegalArgumentException {
        this.id = id;
        this.date = new Date();
        this.URL = URL;
        this.name = name;
        this.body = body;
    }

    public News(String name, String URL, String body) throws IllegalArgumentException {
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

    public String getURL() {
        return URL;
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }
}
