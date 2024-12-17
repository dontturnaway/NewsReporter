package com.decode.newsreporter.infrastructure.factory;

import com.decode.newsreporter.domain.entity.News;
import com.decode.newsreporter.domain.service.news_parser.parsing_strategy.WrongUrlProvided;
import com.decode.newsreporter.domain.value_object.NewsName;
import com.decode.newsreporter.domain.value_object.URL;
import com.decode.newsreporter.infrastructure.entity.NewsORM;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsConvertFactory {

    public News createNews(NewsORM newsORM) {
        try {
            return new News(newsORM.getId(),
                    new URL(newsORM.getURL()),
                    new NewsName(newsORM.getName()),
                    newsORM.getBody());
        } catch (WrongUrlProvided e) {
            throw new RuntimeException("Failed to deserialize News object from DB");
        }
    }

    public List<News> createNews(List<NewsORM> newsORM) {
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
}
