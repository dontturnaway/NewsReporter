package com.decode.newsreporter.Domain.Factory;

import com.decode.newsreporter.Domain.Entity.News;

public interface NewsFactoryInterface {
    News createNews(String name, String url, String body) throws IllegalArgumentException;
}
