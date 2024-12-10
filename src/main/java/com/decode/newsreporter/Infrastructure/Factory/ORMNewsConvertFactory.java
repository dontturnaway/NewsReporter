package com.decode.newsreporter.Infrastructure.Factory;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Infrastructure.Entity.NewsORM;
import org.springframework.stereotype.Component;

@Component
public class ORMNewsConvertFactory {

    public NewsORM createORMNews(News news) {
        return new NewsORM(news.getId(), news.getDate(), news.getURL().getUrl(), news.getName(), news.getBody());
    }
}
