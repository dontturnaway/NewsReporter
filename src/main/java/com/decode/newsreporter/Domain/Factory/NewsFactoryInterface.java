package com.decode.newsreporter.Domain.Factory;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.ValueObject.URL;

public interface NewsFactoryInterface {
    News createNews(String name, URL url, String body) throws IllegalArgumentException;
}
