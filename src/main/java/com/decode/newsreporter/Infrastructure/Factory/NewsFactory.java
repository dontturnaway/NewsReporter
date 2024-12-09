package com.decode.newsreporter.Infrastructure.Factory;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Factory.NewsFactoryInterface;
import org.springframework.stereotype.Component;

@Component
public class NewsFactory implements NewsFactoryInterface {

    @Override
    public News createNews(String name, String url, String body) throws IllegalArgumentException {
        return new News(name, url, body);
    }
}
