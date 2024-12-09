package com.decode.newsreporter.Infrastructure.Factory;


import com.decode.newsreporter.Domain.Entity.News;
import com.decode.newsreporter.Infrastructure.Entity.NewsORM;
import org.springframework.stereotype.Component;

@Component
public class NewsConvertFactory {

    public News createNews(NewsORM newsORM) {
        return new News(newsORM.getId(), newsORM.getURL(), newsORM.getName(), newsORM.getBody());
    }

}
