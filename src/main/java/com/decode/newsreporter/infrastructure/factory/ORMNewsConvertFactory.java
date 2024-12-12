package com.decode.newsreporter.infrastructure.factory;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import org.springframework.stereotype.Component;

@Component
public class ORMNewsConvertFactory {

    public NewsORM createORMNews(News news) {
        return new NewsORM(news.getId(),
                            news.getDate(),
                            news.getURL().getUrl(),
                            news.getNewsName().getName(),
                            news.getBody());
    }
}
