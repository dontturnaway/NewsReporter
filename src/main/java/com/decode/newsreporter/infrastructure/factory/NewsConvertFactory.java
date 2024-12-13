package com.decode.newsreporter.infrastructure.factory;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.infrastructure.service.WrongUrlProvided;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConvertFactory {

    public News convertNewsFromORM(NewsORM newsORM) {
        try {
            return new News(newsORM.getId(),
                    new URL(newsORM.getURL()),
                    new NewsName(newsORM.getName()),
                    newsORM.getBody());
        } catch (WrongUrlProvided e) {
            throw new RuntimeException("Failed to deserialize News object from DB");
        }
    }

    public List<News> convertNewsFromORM(List<NewsORM> newsORM) {
        List<News> newsList = new ArrayList<>();
        newsORM.forEach(newsORMitem -> {
            try {
                newsList.add(new News(newsORMitem.getId(),
                        new URL(newsORMitem.getURL()),
                        new NewsName(newsORMitem.getName()),
                        newsORMitem.getBody())
                );
            } catch (WrongUrlProvided e) {
                throw new RuntimeException("Failed to deserialize News object from DB");
            }
        });

        return newsList;
    }

    public NewsORM convertNewsToORM(News news) {
        return new NewsORM(news.getId(),
                news.getDate(),
                news.getURL().getUrl(),
                news.getNewsName().getName(),
                news.getBody());
    }
}
