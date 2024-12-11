package com.decode.newsreporter.Infrastructure.Factory;

import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Domain.Service.ParsingStrategy.WrongUrlProvided;
import com.decode.newsreporter.Domain.ValueObject.NewsName;
import com.decode.newsreporter.Domain.ValueObject.URL;
import com.decode.newsreporter.Infrastructure.Entity.NewsORM;
import org.springframework.stereotype.Component;

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

}
